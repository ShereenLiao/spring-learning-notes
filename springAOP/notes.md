# A0P: Aspect Oriented Programming: 面向切片编程
一种编程范式，指导开发者如何组织程序结构

作用：在不修改原始设计的基础上进行功能增强
理念：无入侵式编程

核心概念：
* Join point（连接点） ：目标对象中的方法，类似数据库表中的一条记录
* PointCut（切点） ：表示连接点的集合，类似数据库中的表
* Advice（通知） ：共性功能，通知指的就是指拦截到连接点之后要执行的代码，通知分为前置、后置、异常、最终、环绕通知五类
* Aspect（切面） ：描述通知与切入点的对应关系，切点、连接点、通知等的载体，它们加起来就是一个切面
* Target object（目标对象） ：包含连接点的对象，也被称作被通知或**被代理对象**
* AOP proxy（AOP 代理对象） ：AOP创建的对象，包含通知
* Weaving（织入） ：把代理逻辑加入到目标对象的过程，叫做织入
* Before advice（前置通知） ：前置通知
* After returning advice（后置通知） ：后置通知
* Around advice（环绕通知） ：前后都有，最常用的一种通知

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
* *: 单个任意符号
* ..: 匹配任何数量字符的重复，如在类型模式中匹配任何数量子包；而在方法参数模式中匹配任何数量参数
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

## AOP获取返回值

1. 抛出异常后获得异常信息
```
    @AfterThrowing(value = "pt2()", throwing = "t")
    public void afterThrowing(Throwable t){
        System.out.println("afterThrowing..." + t);
    }
```
2. around advice通过对原始方法的调用，获得原始方法的返回值
```
   @Around("pt2()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        Object [] args = pjp.getArgs();
        System.out.println(Arrays.toString(args));
        args[0] = 666;
        Object ret = pjp.proceed(args);
        return ret;
    }
```

## AOP获得参数
1. JoinPoint对象描述了连接点方法的运行状态，可以用getArgs()获得原始方法的调用参数
```
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

```
2. ProceedingJoinPoint是JoinPoint的子类
```
@Around("pt2()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        Object [] args = pjp.getArgs();
        System.out.println(Arrays.toString(args));
        args[0] = 666;
        Object ret = pjp.proceed(args);
        return ret;
    }
```

## AOP 执行顺序
5.2.7 之前
around-start -> before -> target method -> around-end -> after -> afterReturning

5.2.7 之后
around-start -> before -> target method -> afterReturning -> after -> around-end

变化：
1. after的执行顺序变到了afterReturning和afterThrowing后面。
2. around-end的执行顺序由在方法执行结束的后面变到了最后。

why?
1. after移动到returning和throwing之后，使after可以处理正常和异常的返回条件，代码更加灵活
2. after中无法返回值。若是想有返回值，需要用around-end。

从Spring5.2.7开始，Spring AOP不再严格按照AspectJ定义的规则来执行advice，而是根据其类型按照从高到低的优先级进行执行：@Around，@Before ，@After，@AfterReturning，@AfterThrowing。

当多条通知都希望在同一连接点上运行时会发生什么？ Spring AOP遵循与AspectJ相同的优先级规则来确定通知执行的顺序。 优先级最高的通知首先“on the way in（进入时）”运行（因此，如果给定两个before 通知，优先级最高的通知首先运行）。

从连接点“On the way out（离开时）”，最高优先级的通知最后运行（因此，给定两个after通知，具有最高优先级的通知将第二个运行）

从springframework5.2.7开始，在同一 @Aspect 类中定义的需要在同一连接点上运行的通知方法根据其通知类型按以下顺序分配优先级：从最高到最低优先级：@Around、@Before、@After、@AfterReturning、@AfterThrowing。但是，请注意，@After 通知方法将在同一切面的任何 @AfterReturning 或 @AfterThrowing 通知方法之后有效地调用，遵循 AspectJ 对 @After 的“After finally advice”语义。

[https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#aop-ataspectj-advice-ordering] springboot Advice Ordering