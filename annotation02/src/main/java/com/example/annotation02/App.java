package com.example.annotation02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookDao bookDao = (BookDao)ctx.getBean("bookDao");
        bookDao.save();
        System.out.println(bookDao);

        BookService bs = (BookService) ctx.getBean(BookService.class);
        bs.serve();

    }

}
