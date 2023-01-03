package com.code.springaop.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class MyAdvice {

    @Pointcut("execution(void com.code.springaop.dao.UserDao.update())") //切入点：当执行到这个方法时
    private void pt(){}


    @Before("pt()")
    public void method(){
        System.out.println(System.currentTimeMillis());
    }

}

