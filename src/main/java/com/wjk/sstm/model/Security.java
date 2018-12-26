package com.wjk.sstm.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "music")
public class Security {
    //    开关
    private String enable;
    //    允许地址
    private String allowUrl;
    //    用户允许功能
    private String loginAllows;

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getAllowUrl() {
        return allowUrl;
    }

    public void setAllowUrl(String allowUrl) {
        this.allowUrl = allowUrl;
    }

    public String getLoginAllows() {
        return loginAllows;
    }

    public void setLoginAllows(String loginAllows) {
        this.loginAllows = loginAllows;
    }
}
