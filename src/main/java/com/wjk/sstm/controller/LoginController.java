package com.wjk.sstm.controller;

import com.alibaba.fastjson.JSONObject;
import com.wjk.sstm.dto.CommonContextDto;
import com.wjk.sstm.model.CommonContext;
import com.wjk.sstm.model.User;
import com.wjk.sstm.service.impl.SecurityServiceImpl;
import com.wjk.sstm.until.Result;
import com.wjk.sstm.until.ResultFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 登入登出控制器
 * wjk
 */
@RestController
@RequestMapping(value = "/api")
public class LoginController {

    @Autowired
    private SecurityServiceImpl securityService;

    private final static Logger log = LoggerFactory.getLogger(LoginController.class);

    /**
     * 登入
     * @param user
     * @param
     * @return
     */
    @PostMapping(value = "/login")
    public Object login(@RequestBody User user, HttpServletRequest request){
        log.info("\n-------------------Method : login--------------------\n");
        try{
            //账号密码认证成功之后创建用户令牌时间为20分钟。并存入redis里
            CommonContextDto commonContext = securityService.createUserContext(user.getAccount(),user.getPassword(),request);
            JSONObject object = new JSONObject();
            object.put("commonContext",commonContext);
            //          createUserContext里已经存了taken了
//          redisTemplate.opsForValue().set(taken,user.getAccount(),6, TimeUnit.HOURS);//以token为key，用户账号为值设置6小时过期时间
            Result result= ResultFactory.buildSuccessResult(object);
            return result;
        }catch (Exception e){
            log.debug(e.getMessage());
            Result result=ResultFactory.buildFailResult(e.getMessage());
            return result;
        }
    }
    /**
     * token过期处理
     * @return
     */
    @PostMapping(value = "/token_expire")
    public Result tokenExpire() {
        log.info("\n-------------------Method : token失效 --------------------\n");
        return ResultFactory.buidResult(50014, "token已过期", "");
    }
    /**
     * 登出
     * @param
     * @return
     */
    @RequestMapping(value = "/logout")
    public Result loginOut(HttpServletRequest request){
        log.info("\n-------------------Method : login--------------------\n");
        String token = request.getHeader("X-Token");
        securityService.deleteToken(token);
        return ResultFactory.buidResult(200,"您已登出","");
    }
}
