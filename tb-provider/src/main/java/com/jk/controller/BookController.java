package com.jk.controller;

import com.jk.model.BookBean;
import com.jk.model.BookTypeBean;
import com.jk.service.BookService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class BookController implements BookService {

    @Resource
    private BookService bookService;

    @Override
    @RequestMapping("/findBookList")
    public List<BookBean> findBookList() {
        return bookService.findBookList();
    }

    @Override
    @RequestMapping("/findTypeList")
    public List<BookTypeBean> findTypeList() {
        return bookService.findTypeList();
    }

    @Override
    @RequestMapping("/saveBook")
    public void saveBook(@RequestBody BookBean bookBean) {
        bookService.saveBook(bookBean);
    }

    @Override
    @RequestMapping("/deleteBook")
    public void deleteBook(Integer id) {
        bookService.deleteBook(id);
    }

    @Override
    @RequestMapping("/findBookInfoById")
    public BookBean findBookInfoById(Integer id) {
        return bookService.findBookInfoById(id);
    }

    @Override
    @RequestMapping("/updateBook")
    public void updateBook(@RequestBody BookBean bookBean) {
        bookService.updateBook(bookBean);
    }


}
