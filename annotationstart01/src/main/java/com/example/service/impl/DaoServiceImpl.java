package com.example.service.impl;

import com.example.dao.Dao;
import com.example.service.DaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("DaoService")
public class DaoServiceImpl implements DaoService {
    @Autowired//自动注入引用类型，不需要set方法, 默认按照类型装配
    //@Qualifier("bookDao")//按照名称装配
    private Dao d;


    @Value("${num}")//使用property注入
    private int num;
    @Override
    public void save(){
        System.out.println("service is saved");
        d.save();
        System.out.println(num);
    }
}
