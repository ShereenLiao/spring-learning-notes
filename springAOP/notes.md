# A0P: Aspect Oriented Programming: 面向切片编程
一种编程范式，指导开发者如何组织程序结构

作用：在不修改原始设计的基础上进行功能增强
理念：无入侵式编程

Join point（连接点） ：目标对象中的方法，类似数据库表中的一条记录

PointCut（切点） ：表示连接点的集合，类似数据库中的表

Advice（通知） ：共性功能，通知指的就是指拦截到连接点之后要执行的代码，通知分为前置、后置、异常、最终、环绕通知五类

Aspect（切面） ：描述通知与切入点的对应关系，切点、连接点、通知等的载体，它们加起来就是一个切面

Target object（目标对象） ：包含连接点的对象，也被称作被通知或**被代理对象**

AOP proxy（AOP 代理对象） ：AOP创建的对象，包含通知

Weaving（织入） ：把代理逻辑加入到目标对象的过程，叫做织入

Before advice（前置通知） ：前置通知

After returning advice（后置通知） ：后置通知

Around advice（环绕通知） ：前后都有，最常用的一种通知

## Dependency
! Problem: spring-context version needs to match jdk version
```xml
<dependencies>
        <dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>6.0.3</version>
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

## AOP 工作流程
1. spring容器启动
2. 读取所有切面配置中的切入点
3. 初始化bean，判定对应类中的方法是否匹配到切入点
   1. 匹配失败，创建对象
   2. 匹配成功，创建原始对象（目标对象）的代理对象
4. 获取bean时
   1. 获取bean，调用对象，完成操作
   2. 代理对象：根据代理对象的运行模式运行原始方法 and 增强的内容， 完成操作

## AOP 切入点表达式
格式：
```
execution(public User com.code.springaop.UserService.update(int))
```
通配：
* *:单个任意符号
* ..: parameters/package，代替参数时不能代替空参数
* +: 子类型

## AOP通知类型
* 前置 @Before
* 后置 @After：即使有异常也会执行
* 环绕 @Around 需要自己设置joinpoint执行位置
```
    @Around("pt()")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("before advice");
        pjp.proceed();
        System.out.println("after advice");
    }
```
* 如果有返回值需要将返回值返回，返回类型是object。
* 环绕通知必须依赖形参ProceedingJoinPoint实现原方法的调用。
* 由于无法得知原始方法是否会抛出异常，因此环绕通知方法必须抛出throwable对象。
```
    @Around("pt()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("before advice");
        Object obj = pjp.proceed();
        System.out.println("after advice");
        return obj;
    }
```
* 返回后 @AfterRunning: 只有在没有抛异常才会继续运行
* 有异常后执行 @AfterThrowing: 有异常才执行








