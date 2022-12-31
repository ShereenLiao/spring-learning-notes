package com.example.annotation02.service.impl;

import com.example.annotation02.BookDao;
import com.example.annotation02.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    @Qualifier("bookDao")
    private BookDao bookDao;

    @Override
    public void serve() {
        bookDao.save();
        System.out.println("book service serve ...");
    }
}
