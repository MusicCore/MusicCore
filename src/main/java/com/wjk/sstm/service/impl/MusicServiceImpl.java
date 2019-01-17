package com.wjk.sstm.service.impl;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.wjk.sstm.mapper.MusicMapper;
import com.wjk.sstm.model.CommonContext;
import com.wjk.sstm.model.Music;
import com.wjk.sstm.model.PageForm;
import com.wjk.sstm.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("musicService")
public class MusicServiceImpl implements MusicService {
    @Autowired
    protected MusicMapper musicMapper;

    @Override
    public void saveMusic(Music music) throws Exception {
        musicMapper.save(music);
    }

    @Override
    public List<Music> listMusic() throws Exception {
        List<Music> list = musicMapper.list();
        return list;
    }

    @Override
    public Music listMusicById(Integer id) throws Exception {
        Music music = musicMapper.listById(id);
        return music;
    }

    @Override
    public void updateMusicInfoById(Music music) throws Exception {
        if (music.getIsModify() ==  1 || music.getAuthorAccount().equals(CommonContext.getInstance().getAccount())){
            music.setLastAuthor(CommonContext.getInstance().getName());
            musicMapper.update(music);
        }else {
            throw new Exception("无权更新");
        }
    }

    @Override
    public List<Music> listMusicByPar(PageForm pageForm) {
        return musicMapper.listByPar(pageForm);
    }
}
