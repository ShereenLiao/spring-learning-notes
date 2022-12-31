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

