package com.wjk.sstm.mapper;

import com.wjk.sstm.model.Music;
import com.wjk.sstm.model.PageForm;
import com.wjk.sstm.provider.MusicProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MusicMapper {
    @Insert("INSERT INTO music_score(" +
            "   id," +
            "   title," +
            "   content_music," +
            "   content_img," +
            "   content_short," +
            "   content," +
            "   author_account," +
            "   author_name," +
            "   create_time," +
            "   update_time," +
            "   last_author," +
            "   is_modify," +
            "   is_delete," +
            "   is_top," +
            "   clicks" +
            ") VALUES(" +
            "   #{id}," +
            "   #{title}," +
            "   #{contentMusic}," +
            "   #{contentImg}," +
            "   #{contentShort}," +
            "   #{content}," +
            "   #{authorAccount}," +
            "   #{authorName}," +
            "   #{createTime}," +
            "   #{updateTime}," +
            "   #{lastAuthor}," +
            "   #{isModify}," +
            "   #{isTop}," +
            "   #{isDelete}," +
            "   #{clicks}" +
            ")")
    public void save(Music param) throws Exception;

    @Select("SELECT * FROM music_score")
    public List<Music> list() throws Exception;

    @Select("SELECT * FROM music_score WHERE id = #{id}")
    public Music listById(Integer id) throws Exception;

    @UpdateProvider(type = MusicProvider.class, method = "updateSQL")
    public void  update(Music param) throws Exception;

    @Select("SELECT * FROM music_score limit #{page.pageStart},#{page.rows}")
    public List<Music> listByPar(@Param("page") PageForm pageForm);
}
