package com.code.controller;

import com.code.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ResponseController {

    @RequestMapping("/toPage")
    public String toPage(){
        System.out.println("jump to index.jsp");
        return "index";
    }


    @RequestMapping("/toText")
    @ResponseBody
    public String toTest(){
        System.out.println("return text");
        return "index";
    }


    @RequestMapping("/toPOJO")
    @ResponseBody
    public User toPOJP(){
        System.out.println("return POJO");
        User user = new User();
        user.setAge(11);
        user.setName("111");
        return user;
    }



}
