package com.wjk.sstm.until;

/**
 * @description 统一 API响应结果封装
 * @author WANGJIHONG
 * @date 2018年3月13日 下午8:44:29
 * @Copyright 版权所有 (c) www.javalsj.com
 * @memo 控制Result权限，构建结果Result对象统一使用com.javalsj.blog.vo.ResultFactory工厂类来创建
 */
public class Result {
    /**
     * 响应状态码
     */
    private int code;
    /**
     * 响应提示信息
     */
    private String message;
    /**
     * 响应结果对象
     */
    private Object data;

    Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
