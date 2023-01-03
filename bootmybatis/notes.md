# 1.2

## entity层、dao层、service层、mapper层、controller层详解


1. entity

   entity就是属性类，通常定义在model层里面，其相当于MVC的M层，属于数据模型层；
   一般的实体类对应一个数据表，其中的属性定义数据表中的字段，实体类的字段数量 >= 数据库表中需要操作的字段数量。

2. dao层 = mapper层

dao层叫做数据访问层，全称为data access object，属于一种比较底层基础得操作，具体到对某个表得增删改查，换句话说，某个dao一定是和数据库中的某一张表一一对应的，而且其中也只是封装了增删改查得方法。
3. service层

service层即为业务逻辑层，可以理解为对一个或者多个dao进行得再次封装，主要是针对具体的问题的操作，把一些数据层的操作进行组合，间接与数据库打交道(提供操作数据库的方法)。要做这一层的话，要先设计接口，再实现类。
4. controller层

负责请求转发，接收页面过来的参数，传给service处理，接到返回值，并再次传给页面。

## MyBatis
```
public static void mybatisFindAll() throws IOException {
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = factory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.findAll();
        System.out.println(userList.toString());
        sqlSession.close();
}
```

## Mybatis + Spring

### Dependency
```
         <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>

        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>1.3.2</version>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.15</version>
        </dependency>
```

### mybatis + springboot : myBatisConfig.class 用来代替mybatis-config.xml
1. 设置Mapper package: @MapperScan("com.code.bootmybatis.mapper")
2. 创建SqlSessionFactory bean(不知道为什么如果不设置TransactionFactory就会报错)
```java
   import org.apache.ibatis.type.Alias;
   import org.mybatis.spring.SqlSessionFactoryBean;
   import org.mybatis.spring.annotation.MapperScan;
   import org.mybatis.spring.mapper.MapperScannerConfigurer;
   import org.springframework.beans.factory.annotation.Value;
   import org.springframework.context.annotation.Bean;
   import org.springframework.context.annotation.Import;
   import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
   import org.springframework.jdbc.datasource.DataSourceTransactionManager;
   
   import javax.sql.DataSource;
   import java.io.IOException;
   
   
   @MapperScan("com.code.bootmybatis.mapper") //Use this annotation to register MyBatis mapper interfaces when using Java Config
   public class mybatisConfig {
       private static SqlSessionFactory javaSqlSessionFactory;
       //获得sqlsession对象
       @Bean
       public static SqlSessionFactory getSqlSessionFactoryUsingJavaAPI(DataSource dataSource) throws IOException {
           if (javaSqlSessionFactory == null) {
               try {
                   TransactionFactory transactionFactory = new JdbcTransactionFactory();
                   Environment environment = new Environment("development", transactionFactory, dataSource);
                   Configuration configuration = new Configuration(environment);
                   javaSqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
               } catch (Exception e) {
                   throw new RuntimeException(e);
               }
           }
           return javaSqlSessionFactory;
       }
       
   }
```

### Druid + springboot: jdbcConfig.class
#### Dependency
```xml
<dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.2.15</version>
</dependency>
```
#### Configuration
```java
package com.code.bootmybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import javax.sql.DataSource;


public class jdbcConfig {
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;


    @Bean
    public DataSource dataSource(){
        DruidDataSource ds = new DruidDataSource();
        ds.setPassword(password);
        ds.setUsername(username);
        ds.setUrl(url);
        ds.setDriverClassName(driver);
        return ds;
    }
}
```

### Junit + Springboot

#### Dependency
```
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>6.0.2</version>
        </dependency>
```

### Test Case:
1. 使用springjunit测试：@RunWith(SpringJUnit4ClassRunner.class)
2. 设置测试环境：@ContextConfiguration(classes = SpringConfig.class)
3. 将要测试的service 自动装配进来： @Autowired
4. 在测试的case前加： @Test
5. 
```java
package com.code.bootmybatis.service;

import com.code.bootmybatis.config.SpringConfig;
import com.code.bootmybatis.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void findAll(){
        System.out.println(userService.findAll().toString());
    }
}

```


