package com.code.springaop.dao.impl;

import com.code.springaop.dao.UserDao;
import org.springframework.stereotype.Repository;


@Repository
public class UserDaoImpl implements UserDao {
    public void save(){
        System.out.println("book save ...");
    }

    public void update(){
        System.out.println("book update ...");
    }
}
