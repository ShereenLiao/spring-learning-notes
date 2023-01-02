# 12.31 annotations development 

## Annotation development:
### Define a java bean using annotations
We can give the bean a name, so that we can use the name to get the bean. \
Or we can use the class to get the bean.
```
@Component("bookDao")
public class BookDaoImpl  implements BookDao {
    @Override
    public void save() {
    System.out.println("book dao save ... ");
    }
}


@Component
public class BookServiceImpl  implements BookService {
    @Override
    public void serve() {
    System.out.println("book serivce serve ... ");
    }
}
```
### In applicationContext.xml, add the line to scan beans in the package.
```
 <context:component-scan base-package="com.example.annotation02.dao"/>
 <context:component-scan base-package="com.example.annotation02.service"/>
 
```

#### Get the bean
```
     ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
     BookDao bookDao = (BookDao)ctx.getBean("bookDao"); //use bean name
     BookService bs = (BookService) ctx.getBean(BookService.class);//use bean class
```

## Pure Annotation Development: use java class to replace configuration.xml.
### Replace a class with applicationContext.xml. 
@Configuration: set the class as configuration class 
@ComponentScan("com.example.annotation02"): set the scan route. Different routes use ',' as delimiter.
```
package com.example.annotation02.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example.annotation02")
public class SpringConfig {
    
}
```
### Change App configuration Context.
```
public class App {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        BookDao bookDao = (BookDao)ctx.getBean("bookDao");
        bookDao.save();
        System.out.println(bookDao);

        BookService bs = (BookService) ctx.getBean(BookService.class);
        bs.serve();
    }
}
```

## Bean Lifecycle

### Singleton or Prototype: Define using @Scope
singleton : 唯一 bean 实例，Spring 中的 bean 默认都是单例的。 \
prototype : 每次请求都会创建一个新的 bean 实例。
```
    @Component("bookDao")
    @Scope("prototype")
public class BookDaoImpl  implements BookDao {
    @Override
    public void save() {
        System.out.println("book dao save ... ");
    }
}
```

### Life Cycle:
```
public class BookDaoImpl  implements BookDao {
    @Override
    public void save() {
        System.out.println("book dao save ... ");
    }

    //run after constructor
    @PostConstruct
    public void init(){
        System.out.println("init ...");
    }

    //run before destructor
    @PreDestroy
    public void destroy(){
        System.out.println("destory ...");
    }
}
```

## Autowired


### Use type: @Autowired
```
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public void serve() {
        bookDao.save();
        System.out.println("book service serve ...");
    }
}
```

### Use name: @Autowired, @Qualifier
```
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    @Qualifier("bookDao")
    private BookDao bookDao;

    @Override
    public void serve() {
        bookDao.save();
        System.out.println("book service serve ...");
    }
}
```

### Primitive Injection: @Value
```
public class BookDaoImpl  implements BookDao {
    @Value("1111")
    private String name;
}
```

### Use external properties
Add @PropertySource("application.properties") in SpringConfig.
```
@Configuration
@ComponentScan("com.example.annotation02")
@PropertySource("application.properties")
public class SpringConfig {
}
```
User properties : @Value("${name}")
```
public class BookDaoImpl  implements BookDao {
    @Value("${name}")
    private String name;
}
```



## Manage Third-party beans

IoC: 控制反转
使用对象时，由主动new产生对象转换为由外部提供对象，此过程对象控制权由程序转移到外部。\
Spring 提供一个容器，成为IoC容器，用来充当IoC思想中的外部。
IoC容器负责对象的创建、初始化等一系列工作，被创建或管理的对象在IoC容器中统称为Bean。\
DI(Dependency Injection)依赖注入：在ioc容器中将有依赖关系的bean进行绑定。


### Get a third party bean: Define a method to get the bean and use @Bean.
```
public class SpringConfig {

    //1.定一个方法获得要管理的对象
    @Bean("dataSource")
    public DataSource dataSource(){
       DruidDataSource ds = new DruidDataSource();
       ds.setDriverClassName("com.mysql.jdbc.Driver");
       ds.setUrl("jdbc:mysql://localhost:3306/spring_db");
       ds.setUsername("root");
       ds.setPassword("root");
       return ds;
    }
}
```

### Manage Multiple Config files. Use @Import({})
```
@Configuration
@ComponentScan("com.example.annotation02")
@PropertySource("application.properties")
@Import({jdbcConfig.class, ... }) //导入配置文件
public class SpringConfig {

    
}
```

### Third-party Bean Dependency Injection:

#### Primitive type: @Value
```
public class jdbcConfig {
    //1.定一个方法获得要管理的对象
    @Value("com.mysql.jdbc.Driver")
    private String driver;

    @Bean("dataSource")
    public DataSource dataSource(){
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(driver);
    }
}
```

#### Reference type: write as input variables
```
@Bean("dataSource")
    public DataSource dataSource(BookDao bookDao){
        System.out.println(bookDao);
        return ds;
    }
```

### Spring and MyBatis

#### Dependency in maven:
```
<dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>3.0.1</version>
</dependency>
<dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.44</version>
</dependency>
<dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>5.2.10.RELEASE</version>
</dependency>
<dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>1.3.0</version>
</dependency>
```

#### Mybatis Connection:
```
public static void main(String[] args) throws IOException {
        //初始化sqlsessionfactory对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = builder.build(inputStream);

        //获取连接，获取mapper
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AccountDao account = sqlSession.getMapper(AccountDao.class);

        //获取数据接口
        Account ac = account.findById(1);
        System.out.println(ac);

        //关闭连接
        sqlSession.close();
}
```
#### MyBatis Config

```
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class MyBatisConfig {
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource){
        SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
        ssfb.setTypeAliasesPackage("com.example.mybatisandspring");
        return ssfb;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer msc = new MapperScannerConfigurer();
        msc.setBasePackage("com.example.mybatisandspring.dao");
        return msc;
    }
}
```






















