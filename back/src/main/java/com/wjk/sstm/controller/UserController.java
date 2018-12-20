package com.wjk.sstm.controller;

import com.wjk.sstm.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/user")
//@RestController包含了@ResponseBody故返回的都是json类型的值，不会跳转jsp页面
public class UserController {

    @PostMapping(value = "login")
    public String login(User user){
        System.out.println(user);
        return "succuess";
    }



}
