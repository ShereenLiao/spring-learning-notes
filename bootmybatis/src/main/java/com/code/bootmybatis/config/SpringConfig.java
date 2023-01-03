package com.code.bootmybatis.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.code.bootmybatis")
@PropertySource("jdbc-config.properties")
@Import({jdbcConfig.class, mybatisConfig.class})
public class SpringConfig {
}
