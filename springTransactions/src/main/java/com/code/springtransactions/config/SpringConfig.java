package com.code.springtransactions.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.code.springtransactions")
@Import({JdbcConfig.class, MybatisConfig.class})
@PropertySource("Jdbc-config.properties")
@EnableTransactionManagement
public class SpringConfig {
}
