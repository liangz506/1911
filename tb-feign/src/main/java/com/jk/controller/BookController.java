package com.jk.controller;

import com.jk.model.BookBean;
import com.jk.model.BookTypeBean;
import com.jk.service.BookFeignService;
import com.jk.util.*;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("book")
public class BookController {

    @Resource
    private BookFeignService bookFeignService;

    @Resource
    private RedisUtil redisUtil;

    @RequestMapping("/toBookPage")
    public String toBookPage(){
        return "book";
    }

    @RequestMapping("/findBookList")
    @ResponseBody
    public List<BookBean> findBookList(){
        List<BookBean> bookList = (List<BookBean>)redisUtil.get(Constant.FIND_BOOK_LIST);
        if (bookList == null || bookList.size() <= 0 || bookList.isEmpty()){
            //从数据库查询  存入redis
           bookList = bookFeignService.findBookList();
           redisUtil.set(Constant.FIND_BOOK_LIST,bookList,30);
        }
        return bookList;
    }

    @RequestMapping("toAddBookPage")
    public String toAddBookPage(){
        return "addbook";
    }

    @RequestMapping("/findTypeList")
    @ResponseBody
    public List<BookTypeBean> findTypeList(){
        return bookFeignService.findTypeList();
    }

    @RequestMapping("/saveBook")
    @ResponseBody
    public void saveBook(BookBean bookBean){
        redisUtil.del(Constant.FIND_BOOK_LIST);
        if (bookBean.getId() != null){
            bookFeignService.updateBook(bookBean);
        }else{
            bookFeignService.saveBook(bookBean);
        }
    }

    @RequestMapping("/saveOrder")
    @ResponseBody
    @HystrixCommand(fallbackMethod = "saveOrderFail")
    public Object saveOrder(Integer userId, Integer productId, HttpServletRequest request) {
        return bookFeignService.save(userId, productId);
    }

    //注意，方法签名一定要要和api方法一致
    public Object saveOrderFail(Integer userId, Integer productId, HttpServletRequest request){

        System.out.println("controller,保存订单降级方法");

        String value =(String) redisUtil.get(Constant.SAVE_ORDER_WARNING_KEY);

        String ipAddr = request.getRemoteAddr();

        //新启动一个线程进行业务逻辑处理
        new Thread( ()->{
            if(StringUtil.isNotEmpty(value)) {
                System.out.println("紧急短信，用户下单失败，请离开查找原因,ip地址是="+ipAddr);

                //发送一个http请求，调用短信服务 TODO
                HashMap<String, Object> headers = new HashMap<String, Object>();
                headers.put("AppKey", Constant.WANGYI_SMS_APP_KEY);
                String nonce = UUID.randomUUID().toString().replaceAll("-", "");
                headers.put("Nonce", nonce);
                String curtime = System.currentTimeMillis()+"";
                headers.put("CurTime", curtime);
                headers.put("CheckSum", CheckSumBuilder.getCheckSum(Constant.WANGYI_SMS_APP_SECRET, nonce, curtime));

                HashMap<String, Object> params = new HashMap<String, Object>();
                params.put("mobile", userId);


                // 写发送短信代码，带有参数发送 userId  productId
                try {
                    String post = HttpClientUtil.post(Constant.WANGYI_SMS_URL, params, headers);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                redisUtil.set(Constant.SAVE_ORDER_WARNING_KEY, "用户保存订单失败", 60);
            }else{
                System.out.println("已经发送过短信，1分钟内不重复发送");
            }
        }).start();

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("code",-1);
        map.put("message","抢购人数太多，您被挤出来了，稍等重试");
        return map;

    }
    /*private Object saveBookFail(BookBean bookBean){
        System.out.println("controller 保存图书降级方法");

        Map<String, Object> map = new HashMap<>();
        map.put("code",-1);
        map.put("msg","人数太多，您被挤出来了,请稍后");
        return map;
    }*/

    @RequestMapping("/deleteBook")
    @ResponseBody
    public void deleteBook(@RequestParam Integer id){
        redisUtil.del(Constant.FIND_BOOK_LIST);
        bookFeignService.deleteBook(id);
    }

    @RequestMapping("toEditBookPage")
    public String toEditBookPage(@RequestParam Integer id, Model model){
        BookBean bookBean = bookFeignService.findBookInfoById(id);
        model.addAttribute("bookBean",bookBean);
        List<BookTypeBean> typeList = bookFeignService.findTypeList();
        model.addAttribute("typeList",typeList);
        return "editbook";
    }
}
