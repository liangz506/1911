package com.jk.service;

import com.jk.model.BookBean;
import com.jk.model.BookTypeBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public interface BookService {

    @RequestMapping("/findBookList")
    List<BookBean> findBookList();

    @RequestMapping("/findTypeList")
    List<BookTypeBean> findTypeList();

    @RequestMapping("/saveBook")
    void saveBook(BookBean bookBean);

    @RequestMapping("/deleteBook")
    void deleteBook(@RequestParam Integer id);

    @RequestMapping("/findBookInfoById")
    BookBean findBookInfoById(@RequestParam Integer id);

    @RequestMapping("/updateBook")
    void updateBook(BookBean bookBean);

    @RequestMapping("/save")
    Object save(@RequestParam Integer userId,@RequestParam Integer productId);
}
