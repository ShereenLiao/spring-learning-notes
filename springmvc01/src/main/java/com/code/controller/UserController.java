package com.code.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class UserController {

    @RequestMapping(value = "/greet", method = RequestMethod.GET)
    @ResponseBody
    public String welcome(ModelMap model) {
        return "{'hello':'1111'}";
    }


}