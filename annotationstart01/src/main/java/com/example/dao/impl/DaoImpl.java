package com.example.dao.impl;
import com.example.dao.Dao;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Repository("Dao")
//@Scope("prototype")//设置成非单例模式，默认是单例模式
public class DaoImpl implements Dao{

    public void save() {
        System.out.printf("save");
    }

    @PostConstruct
    public void init(){
        System.out.println("Init dao");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("destroy");
    }
}
