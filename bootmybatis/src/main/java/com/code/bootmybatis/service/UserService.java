package com.code.bootmybatis.service;

import com.code.bootmybatis.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService {
    List<User> findAll();

}
