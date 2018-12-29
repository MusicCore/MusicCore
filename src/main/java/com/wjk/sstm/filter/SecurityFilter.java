package com.wjk.sstm.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPObject;
import com.wjk.sstm.until.RedisUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Service("SecurityFilter")
public class SecurityFilter implements HandlerInterceptor {

    private final static Logger log = Logger.getLogger(SecurityFilter.class);
    @Autowired
    private StringRedisTemplate redisTemplate;
    //    @Resource
//    private RedisUtils redisUtils;
//    在请求处理之前进行调用（Controller方法调用之前)
//    该方法的返回值是布尔值Boolean类型的，当它返回为false 时，表示请求结束，后续的Interceptor 和Controller 都不会再执行；
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle : RequestUrl:"+request.getRequestURI());
//        log.info("preHandle : RequestedSessionId:"+request.getServletPath());
//        新增判断vue程序来请求的时候携带token让其通过
//        获取请求头里面的taken
        String token = request.getHeader("X-Token");
//        String token = request.getParameter("token");
        if(token != null){
            if(redisTemplate.opsForValue().get(token) != null) {
                return true;
            }else{
                response.sendRedirect("/api/token_expire");
                return false;
            }
        }
//        response.sendRedirect("/api/login");
        return false;
//        HttpSession session = request.getSession();
//        if (session.getAttribute("loginAccount")!=null) {
//            try {
//                String loginSessionId = redisTemplate.opsForValue().get(session.getAttribute("loginAccount"));
//                if (loginSessionId != null && loginSessionId.equals(session.getId())) {
//                    log.info("-- ---------success login--------------");
//                    return true;
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }

    //    该方法将在请求处理之后，也就是在Controller 方法调用之后被调用，但是会在视图返回被渲染之前被调用
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }
    //    顾名思义，该方法将在整个请求结束之后，也就是在DispatcherServlet 渲染了对应的视图之后执行。这个方法的主要作用是用于进行资源清理工作的。
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }

}
