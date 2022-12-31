package com.example.annotation02.service.impl;

import com.example.annotation02.BookService;
import org.springframework.stereotype.Component;

@Component
public class BookServiceImpl implements BookService {

    @Override
    public void serve() {
        System.out.println("book service serve ...");
    }
}
