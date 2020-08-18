package com.jk.service;

import com.jk.model.BookBean;
import com.jk.model.BookTypeBean;

import java.util.List;

public interface BookService {

    List<BookBean> findBookList();

    List<BookTypeBean> findTypeList();

    void saveBook(BookBean bookBean);

    void deleteBook(Integer id);

    BookBean findBookInfoById(Integer id);

    void updateBook(BookBean bookBean);
}
