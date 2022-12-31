package com.example.annotation02.dao.impl;

import com.example.annotation02.BookDao;
import org.springframework.stereotype.Component;

@Component("bookDao")
public class BookDaoImpl  implements BookDao {

    @Override
    public void save() {
        System.out.println("book dao save ... ");
    }
}
