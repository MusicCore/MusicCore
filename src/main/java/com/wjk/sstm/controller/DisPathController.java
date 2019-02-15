package com.wjk.sstm.controller;

import com.wjk.sstm.model.Music;
import com.wjk.sstm.service.MusicService;
import com.wjk.sstm.until.Result;
import com.wjk.sstm.until.ResultFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * 页面跳转
 * wjk
 */
@Controller
public class DisPathController {

    /**
     * 注册页面页面跳转
     * @param model
     * @return
     */
    @GetMapping(value = "/api/register")
    public ModelAndView goRegister(Model model){ return (new ModelAndView("register"));}

    @Resource(name = "musicService")
    private MusicService musicService;


    @GetMapping(value = "/music/musicserch")
    public String goMsusicserch(){ return "musicserch.html";}


    @GetMapping(value = "/music/musicdetail")
    public String goMusicdetail(){ return "musicdetail.html";}

}
