package com.wjk.sstm.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private Integer id;
    private String account;
    private String password;
    private String name;
    private String roles;
    private String avatar;
    private Date createtime;
    private Date updatetime;
    private String active;
}
