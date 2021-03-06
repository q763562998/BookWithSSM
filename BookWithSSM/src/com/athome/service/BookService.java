package com.athome.service;

import com.athome.pojo.BookBean;

import java.util.List;

public interface BookService {
    public List<BookBean> getAllBook();

    public BookBean getById(int id);

    public void updateBook(BookBean bookBean);

    public void deleteBook(Integer id);

    public void insertBook(BookBean bookBean);
}
