package com.woai662.demo.controller;

import com.woai662.demo.entity.User;
import com.woai662.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "/user")
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "index")
    public String index() {
        return "user/index";
    }

    @RequestMapping(value = "show",method = RequestMethod.GET)
    @ResponseBody
    public String show(@RequestParam(value = "name") String name){
        User user = userService.findUserByName(name);
        if (null != user) {
            return user.getUserid() + "/" + user.getName() + "/" + user.getPassword();
        }
        else{
            return "null(String)!";
        }
    }


    @RequestMapping(value = "post",method = RequestMethod.POST)
    public String post() {
        try {
            Thread.sleep(1000 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "user/post";
    }





}
