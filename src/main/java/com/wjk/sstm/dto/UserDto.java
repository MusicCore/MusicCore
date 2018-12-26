package com.wjk.sstm.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private Integer id;
    private String account;
    private String password;
    private String name;
    private String roles;
    private String avatar;
    private Date createtime;
    private Date updatetime;
    private String active;
    private String token;
}
