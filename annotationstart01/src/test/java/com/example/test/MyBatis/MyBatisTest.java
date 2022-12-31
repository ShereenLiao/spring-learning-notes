package com.example.test.MyBatis;
import com.example.data.Brand;
import com.example.data.User;
import com.example.mapper.BrandMapper;
import com.example.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public class MyBatisTest {
    @Test
    public void testSelectALL() throws IOException {
        //1.加载mybatis核心配置文件，获取sqlsessionfactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取sqlsession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        BrandMapper userMapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = userMapper.selectAll();
        System.out.println(brands);
    }

    @Test
    public void testSelectById() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取sqlsession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        BrandMapper userMapper = sqlSession.getMapper(BrandMapper.class);
        Brand brand = userMapper.selectById(1);
        System.out.println(brand);
    }

    @Test
    public void testSelectByCondition() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取sqlsession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        BrandMapper userMapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands= userMapper.selectByCondition(1, "%c%", "%b%");
        System.out.println(brands);

        List<Brand> brands2= userMapper.selectByCondition(new Brand("%b%", "%c%", 1));
        System.out.println(brands2);

        HashMap map = new HashMap<>();
//        map.put("status", 1);
//        map.put("brandName", "%b%");
        map.put("companyName", "%c%");
        List<Brand> brands3= userMapper.selectByCondition(map);
        System.out.println(brands);

    }


    @Test
    public void testSelectBySingleCondition() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取sqlsession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        Brand b = new Brand();
//        b.setBrandName("b1");
        List<Brand> brands = brandMapper.selectBySingleCondition(b);
        System.out.println(brands);

    }

    @Test
    public void testAdd() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取sqlsession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//设置为自动commit 事务

        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        System.out.println("before add:");
        System.out.println(brandMapper.selectAll());
        Brand b = new Brand();
        b.setBrandName("b4");
        b.setDescription("food");
        b.setCompanyName("c3");
        b.setStatus(1);
        brandMapper.add(b);
        sqlSession.commit(); //commit
        System.out.println(b.getId());
        System.out.println("after add:");
        System.out.println(brandMapper.selectAll());

    }

    @Test
    public void testUpdate() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取sqlsession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//设置为自动commit 事务

        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        System.out.println("before update:");
        System.out.println(brandMapper.selectAll());
        Brand b = new Brand();
        b.setBrandName("b44");
//        b.setDescription("food");
//        b.setCompanyName("c3");
        b.setStatus(1);
        b.setId(5);
        brandMapper.update(b);

        sqlSession.commit(); //commit
        System.out.println("after update:");
        System.out.println(brandMapper.selectAll());

    }


    @Test
    public void testDeleteById() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取sqlsession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//设置为自动commit 事务

        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        System.out.println("before update:");
        System.out.println(brandMapper.selectAll());

        brandMapper.deleteById(1);

        System.out.println("after update:");
        System.out.println(brandMapper.selectAll());

    }


    @Test
    public void testDeleteByIds() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取sqlsession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//设置为自动commit 事务

        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        System.out.println("before update:");
        System.out.println(brandMapper.selectAll());

        brandMapper.deleteByIds(new int[]{1, 2});

        System.out.println("after update:");
        System.out.println(brandMapper.selectAll());

    }







}
