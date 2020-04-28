package com.athome.service;

import com.athome.mapper.BookMapper;
import com.athome.pojo.BookBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    BookMapper bookMapper;


    @Override
    public List<BookBean> getAllBook() {
        List<BookBean> list = bookMapper.getAllBook();
        return list;
    }

    @Override
    public BookBean getById(int id) {
        BookBean book = bookMapper.getById(id);
        return book;
    }

    @Override
    public void updateBook(BookBean bookBean) {
        bookMapper.updateBook(bookBean);
    }

    @Override
    public void deleteBook(Integer id) {
        bookMapper.deleteBook(id);
    }

    @Override
    public void insertBook(BookBean bookBean) {
        bookMapper.insertBook(bookBean);
    }
}
