# 12.31 annotations development 

## Define a java bean using annotations
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
In applicationContext.xml, add the line to scan beans in the package.
```
 <context:component-scan base-package="com.example.annotation02.dao"/>
 <context:component-scan base-package="com.example.annotation02.service"/>
 
```

### Get the bean
```
     ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
     BookDao bookDao = (BookDao)ctx.getBean("bookDao"); //use bean name
     BookService bs = (BookService) ctx.getBean(BookService.class);//use bean class
```
