package com.example.annotation02;

import com.example.annotation02.config.SpringConfig;
import com.example.annotation02.dao.impl.BookDaoImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        BookService bs = (BookService)ctx.getBean(BookService.class);
        bs.serve();
        BookDaoImpl bd = (BookDaoImpl)ctx.getBean("bookDao");
        bd.print();
        ctx.close();
    }

}
