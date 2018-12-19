package com.wjk.sstm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
//@RestController包含了@ResponseBody故返回的都是json类型的值，不会跳转jsp页面
public class UserController {
    /**
     *
     * 登入跳转
     * @return
     */
    @GetMapping(value = "/login")
    public ModelAndView loginTest(Model model){
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }
}
