package com.code.springtransactions.config;


import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.apache.ibatis.session.Configuration;

import javax.sql.DataSource;
import java.io.IOException;


@MapperScan("com.code.springtransaction.mapper")
public class MybatisConfig {
//    private static SqlSessionFactory javaSqlSessionFactory;
//    //获得sqlsession对象
//    @Bean
//    public static SqlSessionFactory getSqlSessionFactoryUsingJavaAPI(DataSource dataSource) throws IOException {
//        if (javaSqlSessionFactory == null) {
//            try {
//                TransactionFactory transactionFactory = new JdbcTransactionFactory();
//                Environment environment = new Environment("development", transactionFactory, dataSource);
//                Configuration configuration = new Configuration(environment);
//                javaSqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
//        return javaSqlSessionFactory;
//    }
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource){
        SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
        ssfb.setTypeAliasesPackage("com.code.springtransactions.entity");
        ssfb.setDataSource(dataSource);
        return ssfb;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer msc = new MapperScannerConfigurer();
        msc.setBasePackage("com.code.springtransactions.mapper");
        return msc;
    }
}
