package com.example.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configurable
@ComponentScan({"com.example.dao", "com.example.service.impl"})
@PropertySource("jdbc.properties") //加入property文件，可以用{}注入多个，不支持*.properties
public class SpringConfig {

}



