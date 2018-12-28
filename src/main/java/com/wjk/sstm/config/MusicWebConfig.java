package com.wjk.sstm.config;

import com.wjk.sstm.filter.SecurityFilter;
import com.wjk.sstm.model.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.security.PublicKey;

@Configuration
public class MusicWebConfig implements WebMvcConfigurer  {
    @Autowired
    SecurityFilter securityFilter;
    @Autowired
    Security security;
//    设定默认访问页面
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/zxc/foo").setViewName("foo");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * 注册安全拦截器
         */
        FilterRegistry(registry);
    }

    /**
     * 注册拦截器 可多注册
     * @param registry
     */
    public void FilterRegistry(InterceptorRegistry registry){
        if(security.getEnable().equals("Y")){
            //      数组初始化
            String []securityFilterStr = new String[]{};
//        获取允许地址
            String allowUrl = security.getAllowUrl();
            if (!StringUtils.isEmpty(allowUrl)){
                securityFilterStr = allowUrl.split(",");
            }
//        注册
//        addPathPatterns 用于添加拦截规则
//        excludePathPatterns 用户排除拦截
            registry.addInterceptor(securityFilter).addPathPatterns("/**").excludePathPatterns(securityFilterStr);
        }

   }
}
