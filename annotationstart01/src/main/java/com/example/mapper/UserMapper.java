package com.example.mapper;

import com.example.data.User;

import java.util.List;

public interface UserMapper {

    List<User> selectAll(); //对应的是usermapper.xml中的select all
}
