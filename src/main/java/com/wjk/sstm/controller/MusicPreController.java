package com.wjk.sstm.controller;

import com.alibaba.fastjson.JSONObject;
import com.wjk.sstm.model.Music;
import com.wjk.sstm.service.MusicService;
import com.wjk.sstm.until.ResultFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class MusicPreController {

    @Resource(name = "musicService")
    private MusicService musicService;

    @GetMapping(value = "/music/index")
    public String goIndex(Model model){
        try {
            List<Music> list = musicService.listMusic();
            model.addAttribute("musiclist",list);
        }catch (Exception e){
            model.addAttribute("error","error");
        }
        return "index.html";
    }

}
