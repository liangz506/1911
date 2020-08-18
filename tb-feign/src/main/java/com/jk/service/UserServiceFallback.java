package com.jk.service;

import com.jk.model.BookBean;
import com.jk.model.BookTypeBean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/error")
@Component
public class UserServiceFallback implements BookFeignService{

    @Override
    public List<BookBean> findBookList() {
        System.out.println("熔断处理，方法查询图书集合");

        //熔断处理 TODO
        return null;
    }

    @Override
    public List<BookTypeBean> findTypeList() {
        System.out.println("熔断处理，查询图书类型集合");
        return null;
    }

    @Override
    public void saveBook(BookBean bookBean) {
        System.out.println("进入了 新增图书熔断器");
    }

    @Override
    public void deleteBook(Integer id) {
        System.out.println("进入了 删除图书熔断器");
    }

    @Override
    public BookBean findBookInfoById(Integer id) {
        System.out.println("进入了 回显 熔断器 ");
        return null;
    }

    @Override
    public void updateBook(BookBean bookBean) {
        System.out.println("进入了 修改图书熔断器");
    }

    @Override
    public Object save(Integer userId, Integer productId) {

        System.out.println("进入了 保存订单 熔断器类");

        return null;
    }
}
