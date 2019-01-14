package com.wjk.sstm.model;

import java.io.Serializable;

/**
 * 上下文
 */
public class CommonContext implements Serializable {

    private final static ThreadLocal<CommonContext> s_threadContext = new ThreadLocal<CommonContext>();

    private String account;

    private String roles;

    private String name;

    private String avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void setInstance(CommonContext cc){
        s_threadContext.set(cc);
    }

    public static CommonContext getInstance(){
        CommonContext cc = s_threadContext.get();
        if (null == cc){
            return new CommonContext();
        }
        return s_threadContext.get();
    }

}
