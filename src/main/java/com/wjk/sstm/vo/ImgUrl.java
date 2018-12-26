package com.wjk.sstm.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 用来返回给vue前端的图片上传功能
 * @Author: yuanci
 * @Date: 2018/12/25 14:24
 * @Version 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImgUrl {
    private String name;
    private String url;
    private String uid;
}
