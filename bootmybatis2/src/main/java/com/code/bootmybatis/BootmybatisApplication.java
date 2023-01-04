package com.code.bootmybatis;

import com.code.bootmybatis.config.SpringConfig;
import com.code.bootmybatis.entity.User;
import com.code.bootmybatis.mapper.UserMapper;
import com.code.bootmybatis.service.Impl.UserServiceImpl;
import com.code.bootmybatis.service.UserService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class BootmybatisApplication {

    public static void main(String[] args) {
        mybatisBootJavaConfig();
    }

    public static void mybatisBootJavaConfig(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService us = ctx.getBean(UserServiceImpl.class);
        List<User> userList  = us.findAll();
        System.out.println(userList.toString());
    }

    public static void mybatisFindAll() throws IOException {
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml.bck");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = factory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.findAll();
        System.out.println(userList.toString());
        sqlSession.close();
    }

}
