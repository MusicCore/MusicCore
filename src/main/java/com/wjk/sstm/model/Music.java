package com.wjk.sstm.model;

import lombok.Data;

@Data
public class Music {
    private Integer id;
    private String title;
    private String contentMusic;
    private String contentImg;
    private String contentShort;
    private String content;
    private String authorAccount;
    private String authorName;
    private String createTime;
    private String updateTime;
    private String lastAuthor;
    private String isModify;
    private String isTop;
    private String isDelete;
    private String clicks;
}
