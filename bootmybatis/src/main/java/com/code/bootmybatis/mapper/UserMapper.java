package com.code.bootmybatis.mapper;

import com.code.bootmybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
public interface UserMapper {
    @Select("select * from tb_users")
    List<User> findAll();
}