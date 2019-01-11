package com.wjk.sstm.filter;

import com.wjk.sstm.dto.CommonContextDto;
import com.wjk.sstm.dto.UserDto;
import com.wjk.sstm.dto.dto;
import com.wjk.sstm.model.CommonContext;
import com.wjk.sstm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Service("SecurityFilter")
public class SecurityFilter implements HandlerInterceptor {

    private final static Logger log = LoggerFactory.getLogger(SecurityFilter.class);
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Resource(name = "userService")
    private UserService userService;

    //    @Resource
//    private RedisUtils redisUtils;
//    在请求处理之前进行调用（Controller方法调用之前)
//    该方法的返回值是布尔值Boolean类型的，当它返回为false 时，表示请求结束，后续的Interceptor 和Controller 都不会再执行；
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle : RequestUrl:"+request.getRequestURI());
//        新增判断vue程序来请求的时候携带token让其通过
//        获取请求头里面的taken
        String token = request.getHeader("X-Token");
        if(token != null){
            System.out.println(redisTemplate.opsForValue().get(token));
            if(redisTemplate.opsForValue().get(token) != null) {
                String uid = redisTemplate.opsForValue().get(token);
                UserDto dto = userService.selectUserById(uid);
                saveContext(dto);
                return true;
            }else{
                response.sendRedirect("/api/token_expire");
                return false;
            }
        }
        return false;
    }

    private void saveContext(UserDto dto){
        CommonContext context = new CommonContext();
        context.setAccount(dto.getAccount());
        context.setName(dto.getName());
        context.setRoles(dto.getRoles());
        CommonContext.setInstance(context);
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
