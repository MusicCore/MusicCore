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
public class DisPathController {

    /**
     * 注册页面页面跳转
     * @param model
     * @return
     */
    @GetMapping(value = "/api/register")
    public ModelAndView goRegister(Model model){ return (new ModelAndView("register"));}

    @GetMapping(value = "/music/index")
    public String goIndex(){ return "index.html";}

    @GetMapping(value = "/music/musiclist")
    public String goMsusiclist(){ return "musiclist.html";}

}
