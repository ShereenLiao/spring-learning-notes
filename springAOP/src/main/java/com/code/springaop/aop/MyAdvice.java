package com.code.springaop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
@Aspect
public class MyAdvice {

    //all methods in service
    @Pointcut("execution(* com.code.springaop.dao.impl.*Impl.update(..))")
    private void pt(){}

    //计算万次service中method的万次运行时间
    @Around("pt()")
    public void runSpeed(ProceedingJoinPoint pjp) throws Throwable{
        long start = System.currentTimeMillis();
        Signature signature = pjp.getSignature();
        String className = signature.getDeclaringTypeName();//package name
        String methodName = signature.getName();//method name

        for(int i = 0; i < 1000; ++i){
            pjp.proceed();
        }
        long end = System.currentTimeMillis();
        System.out.println("业务层万次执行时间："+className+"."+methodName+":"+ (end - start)+"ms");
    }


    //获取参数
    @Pointcut("execution(* com.code.springaop.dao.impl.UserDaoImpl.findName(..)))")
    private void pt2(){};

    @Before("pt2()")
    public void before(JoinPoint jp){
        Object [] args = jp.getArgs();
        System.out.println(Arrays.toString(args));
        System.out.println("before advice");
    }

    @After("pt2()")
    public void after(JoinPoint jp){
        Object [] args = jp.getArgs();
        System.out.println(Arrays.toString(args));
        System.out.println("after advice");
    }

    @Around("pt2()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        Object [] args = pjp.getArgs();
        System.out.println(Arrays.toString(args));
        args[0] = 666;
        Object ret = pjp.proceed(args);
        return ret;
    }

    @AfterReturning(value = "pt2()", returning = "ret")
    public void afterReturning(String ret){
        System.out.println("afterReturning ..." + ret);
    }

    @AfterThrowing(value = "pt2()", throwing = "t")
    public void afterThrowing(Throwable t){
        System.out.println("afterThrowing..." + t);
    }


    //3. 执行顺序
    @Pointcut("execution(void com.code.springaop.dao.impl.UserDaoImpl.test())")
    private void pointCutMethod(){}

    @Before("pointCutMethod()")
    public void doBefore(JoinPoint point) {
        System.out.println("Aop1Aspect:doBefore");
        return;
    }

    //声明最终通知
    @After("pointCutMethod()")
    public void doAfter() {
        System.out.println("Aop1Aspect:doAfter");
    }

    //声明后置通知
    @AfterReturning(pointcut = "pointCutMethod()", returning = "returnValue")
    public void doAfterReturning(JoinPoint point, Object returnValue) {
        System.out.println("Aop1Aspect:doAfterReturning");
    }

    //声明例外通知
    @AfterThrowing(pointcut = "pointCutMethod()", throwing = "e")
    public void doAfterThrowing(Exception e) {
        System.out.println("Aop1Aspect:doAfterThrowing");
    }



    //声明环绕通知
    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("Aop1Aspect:doAround-start");
        Object obj = pjp.proceed();
        System.out.println("Aop1Aspect:doAround-end");
        return obj;
    }



}

