package com.code.springaop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class MyAdvice {

    @Pointcut("execution(* *..update(..))") //切入点：当执行到这个方法时
    private void pt(){}


//    @Before("pt()")
//    public void method(){
//        System.out.println(System.currentTimeMillis());
//    }

    @Around("pt()")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("before advice");
        pjp.proceed();
        System.out.println("after advice");
    }

}

