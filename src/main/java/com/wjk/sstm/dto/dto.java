package com.wjk.sstm.dto;

import lombok.Data;

@Data
public class dto {
    private String token;
    //token创建时间
    private Long tokenCreatedTime;
    //失效时间
    private Long tokenExpiryTime;
    //是否登入
    private String isLogin;
}
