package com.wjk.sstm.service.impl;

import com.wjk.sstm.mapper.MusicMapper;
import com.wjk.sstm.model.Music;
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
}
