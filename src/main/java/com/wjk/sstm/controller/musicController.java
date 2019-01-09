package com.wjk.sstm.controller;

import com.wjk.sstm.mapper.MusicMapper;
import com.wjk.sstm.model.Music;
import com.wjk.sstm.until.Result;
import com.wjk.sstm.until.ResultFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 谱子
 * wjk
 */
@RestController
@RequestMapping(value = "/article")
public class musicController {
    @Autowired
    private MusicMapper musicMapper;


    private final static Logger log = LoggerFactory.getLogger(musicController.class);
    @PostMapping(value = "/create")
    public Result musiccreate(@RequestBody Music music){
        musicMapper.save(music);
        log.info("------------method:musiccreate-------------");
        return ResultFactory.buildSuccessResult("");
    }
}
