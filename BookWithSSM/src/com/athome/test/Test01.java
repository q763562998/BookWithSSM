package com.athome.test;

import com.athome.pojo.BookBean;
import com.athome.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
public class Test01 {


    @Autowired
    static BookServiceImpl bookService;
    public static void main(String[] args) {
        List<BookBean> book = bookService.getAllBook();
        System.out.println(book);
    }
}
