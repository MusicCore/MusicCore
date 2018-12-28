package com.wjk.sstm.controller;

import com.wjk.sstm.until.Result;
import com.wjk.sstm.until.ResultFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/api")
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
     * 注册页面页面跳转
     * @param model
     * @return
     */
    @GetMapping(value = "/register")
    public ModelAndView goRegister(Model model){ return (new ModelAndView("register"));}
    /**
     * 密码找回页面跳转
     * @param model
     * @return
     */
    @GetMapping(value = "/forget")
    public ModelAndView goForget(Model model){ return (new ModelAndView("forget"));}

}
