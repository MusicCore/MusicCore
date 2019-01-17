package com.wjk.sstm.service;

import com.wjk.sstm.model.Music;
import com.wjk.sstm.model.PageForm;

import java.util.List;

public interface MusicService {

    /**
     * 保存
     * @param music
     * @throws Exception
     */
    public void saveMusic(Music music) throws Exception;

    /**
     * 读取
     * @return
     * @throws Exception
     */
    public List<Music> listMusic() throws Exception;

    /**
     * 查询
     * @return
     * @throws Exception
     */
    public Music listMusicById(Integer id) throws Exception;

    /**
     * 更新
     * @param music
     * @throws Exception
     */
    public void  updateMusicInfoById(Music music) throws Exception;

    /**
     * 通过分页查询（其他参数待加
     * @param pageForm
     * @return
     */
    public List<Music>  listMusicByPar(PageForm pageForm);

}
