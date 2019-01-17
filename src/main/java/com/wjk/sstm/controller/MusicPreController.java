package com.wjk.sstm.controller;

import com.alibaba.fastjson.JSONObject;
import com.wjk.sstm.model.Music;
import com.wjk.sstm.model.PageForm;
import com.wjk.sstm.service.MusicService;
import com.wjk.sstm.until.Result;
import com.wjk.sstm.until.ResultFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class MusicPreController {

    @Resource(name = "musicService")
    private MusicService musicService;

    @GetMapping(value = "/music/index")
    public String goIndex(Model model, PageForm pageForm){
        pageForm.setPageStart(1);
        pageForm.setRows(5);
        try {
            List<Music> list = musicService.listMusicByPar(pageForm);
            model.addAttribute("musiclist",list);
        }catch (Exception e){
            model.addAttribute("error","error");
        }
        return "index.html";
    }

    @GetMapping(value = "/music/musiclist")
    @ResponseBody
    public Result getMusicList(PageForm PageForm){
        List<Music> list = musicService.listMusicByPar(PageForm);
        return ResultFactory.buildSuccessResult(list);
    }

}
