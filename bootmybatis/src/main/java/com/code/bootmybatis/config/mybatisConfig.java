package com.code.bootmybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.code.bootmybatis.entity.User;
import com.code.bootmybatis.mapper.UserMapper;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
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
