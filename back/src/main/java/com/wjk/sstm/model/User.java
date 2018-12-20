package com.wjk.sstm.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String userName;
    private String passWord;
    private String name;
    private String business;
    private String email;
    private String headPicture;
    private Date createDate;
    private Date updateDate;
    private int state;
}
