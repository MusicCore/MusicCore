package com.wjk.sstm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class DisPathController {

    /**
     *
     * 登入跳转
     * @return
     */
    @GetMapping(value = "/login")
    public ModelAndView goLogin(Model model){
        return (new ModelAndView("login"));
    }

    /**
     * 密码找回页面跳转
     * @param model
     * @return
     */
    @GetMapping(value = "/forget")
    public ModelAndView goForget(Model model){ return (new ModelAndView("forget"));}
}
