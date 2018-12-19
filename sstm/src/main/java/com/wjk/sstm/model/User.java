package com.wjk.sstm.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String userName;
    private String passWord;
    private String realName;
    private String business;
    private String email;
    private String headPicture;
    private Date addDate;
    private Date updateDate;
    private int state;
}
