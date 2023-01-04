package com.code.springaop;

import com.code.springaop.config.SpringConfig;
import com.code.springaop.dao.UserDao;
import com.code.springaop.dao.impl.UserDaoImpl;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.SpringVersion;

@SpringBootApplication
public class SpringAopApplication {
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
		System.out.println(SpringVersion.getVersion());
		System.out.println(SpringBootVersion.getVersion());
		UserDaoImpl us = ctx.getBean(UserDaoImpl.class);
		us.test();
	}



}
