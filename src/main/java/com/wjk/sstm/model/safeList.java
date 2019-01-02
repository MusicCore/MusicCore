package com.wjk.sstm.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "safe")
public class safeList {
    private String safePath;
    private String enable;
}
