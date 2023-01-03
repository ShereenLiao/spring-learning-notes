package com.code.springaop;

import com.code.springaop.config.SpringConfig;
import com.code.springaop.dao.UserDao;
import com.code.springaop.dao.impl.UserDaoImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SpringAopApplication {
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
		UserDao us = ctx.getBean(UserDaoImpl.class);
		us.update();
	}

}
