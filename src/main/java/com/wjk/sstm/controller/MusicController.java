package com.wjk.sstm.controller;

import com.alibaba.fastjson.JSONObject;
import com.wjk.sstm.model.CommonContext;
import com.wjk.sstm.model.Music;
import com.wjk.sstm.model.listQuery;
import com.wjk.sstm.service.MusicService;
import com.wjk.sstm.until.Result;
import com.wjk.sstm.until.ResultFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * 谱子
 * wjk
 */
@RestController
@RequestMapping(value = "api/article")
public class MusicController {
    private final static Logger log = LoggerFactory.getLogger(MusicController.class);

    @Resource(name = "musicService")
    private MusicService musicService;

    /**
     * 获取修改页面信息
     * @param id
     * @return
     */
    @GetMapping(value = "/detail")
    public Result musicEidt(@RequestParam Integer id) {
        log.info("------------method:musicEidt-------------");
        try {
            Music music = musicService.listMusicById(id);
            JSONObject object = new JSONObject();
            object.put("data",music);
            return  ResultFactory.buildSuccessResult(object);
        }catch (Exception e) {
            log.info("简谱查询错误："+e.getMessage());
            return ResultFactory.buildFailResult(e.getMessage());
        }
    }

    /**
     * 更新
     * @param music
     * @return
     */
    @PostMapping(value = "/update")
    public Result musicUpdate(@RequestBody Music music){
        log.info("------------method:musicUpdate-------------");
        try {
            musicService.updateMusicInfoById(music);
            return  ResultFactory.buildSuccessResult("");
        }catch (Exception e){
            log.info("简谱更新错误："+e.getMessage());
            return ResultFactory.buildFailResult(e.getMessage());
        }
    }

    /**
     * 获取信息
     * @param page
     * @param limit
     * @return
     */
    @GetMapping(value = "/list")
    public Result musicList(@RequestParam Integer page,@RequestParam Integer limit){
        log.info("------------method:musicList-------------");
        try {
            List<Music> list = musicService.listMusic();
            JSONObject object = new JSONObject();
            object.put("items",list);
            object.put("total",1);
            return ResultFactory.buildSuccessResult(object);
        }catch (Exception e){
            log.info("简谱查询错误："+e.getMessage());
            return ResultFactory.buildFailResult(e.getMessage());
        }
    }

    /**
     * 插入信息
     */
    @PostMapping(value = "/create")
    public Result musicCreate(@RequestBody Music music) {
        log.info("------------method:musicCreate-------------");
        try{
            insData(music);
            musicService.saveMusic(music);
            return ResultFactory.buildSuccessResult("");
        }catch (Exception e){
            log.info("简谱上传存入错误："+e.getMessage());
            return ResultFactory.buildFailResult(e.getMessage());
        }
    }
    /**
     * 数据装载
     * @param music
     */
    public Music insData(Music music){
        //默认系统时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd  HH:mm:ss");
        Date date = new Date();
        if(null==music.getAuthorAccount())
            music.setAuthorAccount(CommonContext.getInstance().getAccount());
        if(null==music.getAuthorName())
            music.setAuthorName(CommonContext.getInstance().getName());
        if(null==music.getContentImg())
            music.setContentImg("/");
        if(null==music.getCreateTime())
            music.setCreateTime(format.format(date));
        if(null==music.getContentShort())
            music.setContentShort("");
        return music;
    }

}


