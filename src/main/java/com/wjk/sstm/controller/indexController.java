package com.wjk.sstm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class indexController {
    @GetMapping(value = "/music/index")
    public String goIndex(){ return "index.html";}

    @GetMapping(value = "/music/musiclist")
    public String goMsusiclist(){ return "musiclist.html";}
}
