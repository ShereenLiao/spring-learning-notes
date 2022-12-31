package com.example.annotation02.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.example.annotation02")
@PropertySource("application.properties")
public class SpringConfig {
}
