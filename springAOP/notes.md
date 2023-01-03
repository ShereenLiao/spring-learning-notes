# A0P: Aspect Oriented Programming: 面向切片编程
一种编程范式，指导开发者如何组织程序结构

作用：在不修改原始设计的基础上进行功能增强
理念：无入侵式编程

Join point（连接点） ：目标对象中的方法，类似数据库表中的一条记录

PointCut（切点） ：表示连接点的集合，类似数据库中的表

Advice（通知） ：共性功能，通知指的就是指拦截到连接点之后要执行的代码，通知分为前置、后置、异常、最终、环绕通知五类

Aspect（切面） ：描述通知与切入点的对应关系，切点、连接点、通知等的载体，它们加起来就是一个切面

Target object（目标对象） ：包含连接点的对象，也被称作被通知或被代理对象

AOP proxy（AOP 代理对象） ：AOP创建的对象，包含通知

Weaving（织入） ：把代理逻辑加入到目标对象的过程，叫做织入

Before advice（前置通知） ：前置通知

After returning advice（后置通知） ：后置通知

Around advice（环绕通知） ：前后都有，最常用的一种通知

## Dependency
```xml
<dependencies>
        <dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>5.2.22.RELEASE</version>
		</dependency>
		<dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjweaver</artifactId>
			<version>1.9.19</version>
		</dependency>
</dependencies>
```
## Join Point Methods and Advice（共性功能）
```java
public class MyAdvice {
    public void method(){
        System.out.println(System.currentTimeMillis());
    }

}
```

## PointCut
当执行到返回值为void的com.code.aop.mapper.UserMapper.update()方法时:
```
@Pointcut("execution(void com.code.aop.mapper.UserMapper.update())") //切入点：当执行到这个方法时
private void pt(){}
```
## Aspect(绑定共性功能和切入点的关系)
共性功能在切入点之前执行:

```java
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAdvice {

    @Pointcut("execution(void com.code.aop.mapper.UserMapper.update())") //切入点：当执行到这个方法时
    private void pt() {
    }


    @Before("pt()")
    public void method() {
        System.out.println(System.currentTimeMillis());
    }

}

```

## Configure: @EnableAspectJAutoProxy
```java
@Configuration
@ComponentScan("com.code.aop")
@EnableAspectJAutoProxy
public class SpringConfig {

}
```

! Problem: spring-context version needs to match jdk version




