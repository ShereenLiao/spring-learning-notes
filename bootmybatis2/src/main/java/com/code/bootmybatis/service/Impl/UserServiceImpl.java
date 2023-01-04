package com.code.bootmybatis.service.Impl;

import com.code.bootmybatis.entity.User;
import com.code.bootmybatis.mapper.UserMapper;
import com.code.bootmybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> findAll(){
        return userMapper.findAll();
    }

}