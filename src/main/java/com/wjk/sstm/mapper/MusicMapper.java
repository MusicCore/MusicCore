package com.wjk.sstm.mapper;

import com.wjk.sstm.model.Music;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MusicMapper {
    @Insert("INSERT INTO music_score(" +
            "   id,title," +
            "   content_music," +
            "   content_img," +
            "   content_short," +
            "   content," +
            "   author_account," +
            "   author_name," +
            "   create_time," +
            "   update_time," +
            "   last_author" +
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
            "   #{lastAuthor}" +
            "   #{isModify}," +
            "   #{isTop}," +
            "   #{isDelete}," +
            "   #{clicks}" +
            ")")
    public void save(Music param);
}
