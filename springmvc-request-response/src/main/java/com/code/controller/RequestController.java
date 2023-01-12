package com.code.controller;

import com.code.entity.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Controller
public class RequestController {

    // query : http://localhost:8080/user?name=111, 可以在函数参数处获得name的值
    // or post request
    @RequestMapping(value = "/user")
    @ResponseBody
    public String query(String name, int age) {
        System.out.println("parameter: name =  " + name +" age ="+age);
        return "{'module':'user delete'}";
    }

    //可以用@RequestParam("name")来指定request中的参数注入到哪一个参数中
    @RequestMapping(value = "/differentParam")
    @ResponseBody
    public String query2(@RequestParam("name") String username, int age) {
        System.out.println("parameter: name =  " + username +" age ="+age);
        return "{'module':'user delete'}";
    }


    //如果request 参数中有对象的attribute，会直接绑定
    //http://localhost:8080/differentParam?name=xiao&age=11
    //User{age: 11, name:'xiao'}
    @RequestMapping(value = "/pojoParam")
    @ResponseBody
    public String query2(User user) {
        System.out.println("parameter: name =  " + user.getName() +" age ="+user.getAge());
        return "{'module':'pojo user get'}";
    }

    //array
    //http://localhost:8080/arrayParam?likes=111&likes=222&likes=333
    @RequestMapping(value = "/arrayParam")
    @ResponseBody
    public String query2(String [] likes) {
        System.out.println(Arrays.toString(likes));
        return "{'module':'array user get'}";
    }

    //List(Json)
    @RequestMapping(value = "/listParam")
    @ResponseBody
    public String listQuery(@RequestBody List<String> likes) {
        System.out.println(likes);
        return "{'module':'list user get'}";
    }


    @RequestMapping(value = "/jsonParam")
    @ResponseBody
    public String jsonRequest(@RequestBody User user) {
        System.out.println("parameter: name =  " + user.getName() +" age ="+user.getAge());
        return "{'module':'pojo user get'}";
    }

    @RequestMapping(value = "/dateParam")
    @ResponseBody
    public String dataRequest(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date) {
        System.out.println("parameter: date = "+ date);
        return "{'module':'date get'}";
    }






}