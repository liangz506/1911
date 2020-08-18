package com.jk.service;

import com.jk.mapper.BookMapper;
import com.jk.model.BookBean;
import com.jk.model.BookTypeBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    @Resource
    private BookMapper bookMapper;

    @Override
    public List<BookBean> findBookList() {
        return bookMapper.findBookList();
    }

    @Override
    public List<BookTypeBean> findTypeList() {
        return bookMapper.findTypeList();
    }

    @Override
    public void saveBook(BookBean bookBean) {
        bookMapper.saveBook(bookBean);
    }

    @Override
    public void deleteBook(Integer id) {
        bookMapper.deleteBook(id);
    }

    @Override
    public BookBean findBookInfoById(Integer id) {
        return bookMapper.findBookInfoById(id);
    }

    @Override
    public void updateBook(BookBean bookBean) {
        bookMapper.updateBook(bookBean);
    }
}
