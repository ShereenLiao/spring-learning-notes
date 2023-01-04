package com.code.springtransactions;

import com.code.springtransactions.config.SpringConfig;
import com.code.springtransactions.mapper.AccountMapper;
import com.code.springtransactions.service.AccountService;
import com.code.springtransactions.service.Impl.AccountServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SpringTransactionsApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        AccountService accountService = ctx.getBean(AccountServiceImpl.class);
        accountService.transfer(1, 2, 50);

    }

}
