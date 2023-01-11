package com.code.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


@Configuration
@ComponentScan({"com.code.service", "com.code.dao"})
//@ComponentScan(value = "com.code",
//        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Control.class)
//)
public class SpringConfig {

}
