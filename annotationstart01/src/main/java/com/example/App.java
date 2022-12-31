package com.example;

import com.example.dao.Dao;
import com.example.service.DaoService;
import com.example.service.impl.DaoServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml.bak");
        DaoService ds = new DaoServiceImpl();


    }
}
