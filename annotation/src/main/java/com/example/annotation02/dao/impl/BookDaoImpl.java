package com.example.annotation02.dao.impl;

import com.example.annotation02.BookDao;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("bookDao")
@Scope("singleton")
public class BookDaoImpl  implements BookDao {
    @Value("${name}")
    private String name;
    @Override
    public void save() {
        System.out.println("book dao save ... ");
    }

    //run after constructor
    @PostConstruct
    public void init(){
        System.out.println("init ...");
    }

    //run before destructor
    @PreDestroy
    public void destroy(){
        System.out.println("destory ...");
    }

    public void print(){
        System.out.println(name);
    }
}
