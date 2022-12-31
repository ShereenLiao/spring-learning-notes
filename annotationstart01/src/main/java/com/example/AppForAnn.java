package com.example;

import com.example.config.SpringConfig;
import com.example.dao.Dao;
import com.example.service.DaoService;
import com.example.service.impl.DaoServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.awt.print.Book;

public class AppForAnn {
    public static void main(String[] args) {
        /**
         * 用springconfig.java来config
         * */
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        DaoService ds = (DaoService) ctx.getBean("DaoService");
        ds.save();
    }

}
