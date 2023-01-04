package com.code.bootmybatis.service;

import com.code.bootmybatis.config.SpringConfig;
import com.code.bootmybatis.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void findAll(){
        System.out.println(userService.findAll().toString());
    }
}
