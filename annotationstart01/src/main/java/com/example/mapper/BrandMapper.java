package com.example.mapper;

import com.example.data.Brand;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

public interface BrandMapper {
    List<Brand> selectAll();

    @Select("select * from tb_user where id = #{id}")//用于实现一些简单的工作
    Brand selectById(int i);
    /***
    多个参数的解决办法
     1.用@Param（"sql语句中的占位符"） 指定每个参数分配给哪一个
     2.对象参数， 对象名称需要和sql中的占位符相同
     3.map参数, key需要时sql中的占位符相同

     多个参数会被封装为map集合

     */
    List<Brand> selectByCondition(@Param("status") int status, @Param("companyName") String companyName, @Param("brandName") String brandName);

    List<Brand> selectByCondition(Brand brand);

    List<Brand> selectByCondition(HashMap m);
    List<Brand> selectBySingleCondition(Brand b);

    void add(Brand b);

    void update(Brand b);

    void deleteById(int id);
    void deleteByIds(int [] ids);
}
