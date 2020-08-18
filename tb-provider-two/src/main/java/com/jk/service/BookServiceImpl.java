package com.jk.service;

import com.jk.mapper.BookMapper;
import com.jk.model.BookBean;
import com.jk.model.BookTypeBean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BookServiceImpl implements BookService{

    @Resource
    private BookMapper bookMapper;

    @Override
    @RequestMapping("/findBookList")
    public List<BookBean> findBookList() {
        return bookMapper.findBookList();
    }

    @Override
    @RequestMapping("/findTypeList")
    public List<BookTypeBean> findTypeList() {
        return bookMapper.findTypeList();
    }

    @Override
    @RequestMapping("/saveBook")
    public void saveBook(@RequestBody BookBean bookBean) {
        bookMapper.saveBook(bookBean);
    }

    @Override
    @RequestMapping("/deleteBook")
    public void deleteBook(Integer id) {
        bookMapper.deleteBook(id);
    }

    @Override
    @RequestMapping("/findBookInfoById")
    public BookBean findBookInfoById(Integer id) {
        return bookMapper.findBookInfoById(id);
    }

    @Override
    @RequestMapping("/updateBook")
    public void updateBook(@RequestBody BookBean bookBean) {
        bookMapper.updateBook(bookBean);
    }

    @Override
    @RequestMapping("/save")
    public Object save(@RequestParam Integer userId, @RequestParam Integer productId) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("orderId", 2);
        map.put("userId", userId);
        map.put("productId", productId);
        map.put("orderNo", "20200815103622123");
        map.put("orderName", "火车");
        map.put("orderPrice", 59);
        map.put("createTime", "2020-08-16 20:39");
        return map;
    }
}
