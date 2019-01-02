package com.wjk.sstm.config;

import com.wjk.sstm.filter.safeFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MusciFilterConfig {

    @Bean
    public FilterRegistrationBean registrationBean(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        //注入过滤器
        registrationBean.setFilter(new safeFilter());
        //拦截规则
        registrationBean.addUrlPatterns("/");
        //过滤器名称
        registrationBean.setName("theFrist");
        //过滤器顺序
        registrationBean.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE);
        return registrationBean;
    }
}
