# Spring MVC

Spring MVC技术与Servlet技术功能相同，都是用来开发java web程序。

## Dependency
```
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>4.0.1</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>1.2.6</version>
        </dependency>
        <dependency>
          <groupId>javax.servlet.jsp</groupId>
          <artifactId>javax.servlet.jsp-api</artifactId>
          <version>2.3.2-b02</version>
          <scope>provided</scope>
        </dependency>
```

## Plugin: Tomcat
mvn tomcat7:run
```
 <plugin>
        <groupId>org.apache.tomcat.maven</groupId>
        <artifactId>tomcat7-maven-plugin</artifactId>
        <version>2.1</version>
        <configuration>
          <port>80</port>
          <path>/</path>
        </configuration>
 </plugin>
```
mvn jetty:run
## Plugin: Jetty
```
<plugin>
        <groupId>org.eclipse.jetty</groupId>
        <artifactId>jetty-maven-plugin</artifactId>
        <version>9.4.45.v20220203</version>
        <configuration>
          <scanIntervalSeconds>10</scanIntervalSeconds>
          <webApp>
            <contextPath>/</contextPath>
          </webApp>
        </configuration>
      </plugin>
```
## Define Controller

```java
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @RequestMapping("/save")
    @RequestBody
    public String save(){
        
    }
}
```

## Configure: SpringmvcConfig.class

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.JstlView;


@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.code")
public class SpringmvcConfig implements WebMvcConfigurer{

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
```
## Configure SpringMVC
在ServletContainers.class中, 在createRootApplicationContext中设置springconfig。
```java
public class ServletContainers extends AbstractDispatcherServletInitializer {

    //加载springmvc容器配置类
    @Override
    protected WebApplicationContext createServletApplicationContext() {
        AnnotationConfigWebApplicationContext rootContext
                = new AnnotationConfigWebApplicationContext();
        rootContext.register(SpringmvcConfig.class);
        return rootContext;
    }
    //设置那些请求归属spring mvc处理
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/save"};
    }
    //加载spring容器配置，不需要spring 容器的时候，return null就可以，因为现在开始用springmvc
    @Override
    protected WebApplicationContext createRootApplicationContext() {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(SpringConfig.class);
        return ctx;
    }
}
```

### Configure SpringMVC: implementation 2
```java

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletContainers extends AbstractAnnotationConfigDispatcherServletInitializer{

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringmvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
```
## Spring MVC And Spring
* Spring: service bean, dao bean
* Spring MVC: controller bean

### Implementation 1
In SpringConfig.class, spring加载的bean设定扫描范围为精准范围
```java
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.code.service", "com.code.dao"})
public class SpringConfig {
    
}
```

### Implementation 2
In SpringConfig.class: scan all beans in com.code except beans whose annotation type are controller.
```java
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import javax.naming.ldap.Control;

@Configuration
//@ComponentScan({"com.code.service", "com.code.dao"})
@ComponentScan(value = "com.code.service", 
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Control.class)
)
public class SpringConfig {

}
```



