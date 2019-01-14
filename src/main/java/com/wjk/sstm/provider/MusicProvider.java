package com.wjk.sstm.provider;

import com.wjk.sstm.model.Music;
import org.apache.ibatis.jdbc.SQL;

public class MusicProvider {

    public String updateSQL(final Music music) {
        return new SQL() {
            {
                UPDATE("music_score");
//                修改时间
                SET("update_time = now() ");
//                最后一次修改人
                SET("last_author = #{lastAuthor} ");
//                标题
                if (null != music.getTitle() ) {
                    SET("title = #{title} ");
                }
//                伴奏/音乐等
                if (null != music.getContentMusic()) {
                    SET("content_music = #{contentMusic} ");
                }
//                内容展示缩略图
                if (null != music.getContentImg() ) {
                    SET("content_img = #{contentImg} ");
                }
//                摘要
                if (null != music.getContentShort() ) {
                    SET("content_short = #{contentShort}");
                }
//                html内容（主要内容）
                if (null != music.getContent() ) {
                    SET("content = #{content} ");
                }
//                1为除作者其他人可修改，0为不可修改
                if (null != music.getIsModify() ) {
                    SET("is_modify = #{isModify} ");
                }
//                1为删除不展示，0为不删除，默认0
                if (null != music.getIsDelete() ) {
                    SET("is_delete = #{isDelete} ");
                }
//                1为置顶，0为不置顶，默认0
                if (null != music.getIsTop()) {
                    SET("is_top = #{isTop}");
                }
//                点击数
                if (null != music.getClicks() ) {
                    SET("clicks = #{clicks}");
                }
                WHERE("id = #{id} ");
            }
        }.toString();
    }
}
