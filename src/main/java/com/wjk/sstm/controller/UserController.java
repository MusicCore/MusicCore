package com.wjk.sstm.controller;

import com.alibaba.fastjson.JSONObject;
import com.wjk.sstm.dto.UserDto;
import com.wjk.sstm.dto.dataDto;
import com.wjk.sstm.mapper.UserMapper;
import com.wjk.sstm.model.User;
import com.wjk.sstm.service.impl.SecurityServiceImpl;
import com.wjk.sstm.service.impl.TokenServiceImpl;
import com.wjk.sstm.until.RedisUtils;
import com.wjk.sstm.until.Result;
import com.wjk.sstm.until.ResultFactory;
import com.wjk.sstm.until.TFM;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api")
//@RestController包含了@ResponseBody故返回的都是json类型的值，不会跳转jsp页面
public class UserController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SecurityServiceImpl securityService;
    @Autowired
    private StringRedisTemplate redisTemplate;
//    @Resource
//    private RedisUtils redisUtils;

    private static Logger log = Logger.getLogger(UserController.class);

    /**
     * 登入
     * @param user
     * @param model
     * @return
     */
    @PostMapping(value = "/login")
    public Object login(@RequestBody User user, Model model,HttpServletRequest request){
        log.info("\n-------------------Method : login--------------------\n");
        try{
            String taken = securityService.createUserContext(user.getAccount(),user.getPassword(),request);
            JSONObject object = new JSONObject();
            object.put("token",taken);// token
            redisTemplate.opsForValue().set(taken,user.getAccount(),6, TimeUnit.HOURS);//以token为key，用户账号为值设置6小时过期时间
            Result result=ResultFactory.buildSuccessResult(object);
            return result;
        }catch (Exception e){
            log.debug(e.getMessage());
            Result result=ResultFactory.buildFailResult(e.getMessage());
            return result;
        }
    }
    @PostMapping(value = "/userlist")
    public Object getUserList(){
        log.info("\n-------------------Method : login--------------------\n");
        try {
            List<User> list = userMapper.getUserlist();
            JSONObject object = new JSONObject();
            object.put("items",list);
            Result result=ResultFactory.buildSuccessResult(object);
            return result;
        }catch (Exception e){
            Result result=ResultFactory.buildFailResult(e.getMessage());
            return result;
        }
    }

    @PostMapping(value = "/info")
    public Object getUserInfo(@RequestParam String account){
        log.info("\n-------------------Method : 取得"+account+"信息--------------------\n");
        try {
            User user = userMapper.getUser(account);
            JSONObject object = new JSONObject();
            object.put("name",user.getName());
            object.put("avatar",user.getAvatar());
            String [] roles = {user.getRoles()};
            object.put("roles",roles);//返回数组格式权限
            Result result=ResultFactory.buildSuccessResult(object);
            return result;
        }catch (Exception e){
            Result result=ResultFactory.buildFailResult(e.getMessage());
            return result;
        }
    }

    @PostMapping(value = "/token_expire")
    public Result tokenExpire() {
        log.info("\n-------------------Method : token失效 --------------------\n");
        return ResultFactory.buidResult(50014,"token已过期","");
    }
    /**
     * 注册
     * @param user
     * @param model
     * @param request
     * @return
     */
    @PostMapping(value = "/register")
    public Object register(User user,Model model,HttpServletRequest request){
        log.info("\n-------------------Method : register--------------------\n");
        try {
            userMapper.selectIsHaveAccount(user);
            Result result=ResultFactory.buildFailResult("账号已存在");
            return result;
        }catch (Exception e){
            try {
                setUser(user);
                userMapper.insert(user);
                Result result=ResultFactory.buildFailResult("注册成功");
                return result;
            }catch (Exception e1){
                Result result=ResultFactory.buildFailResult(e1.getMessage());
                return result;
            }
        }
    }
    /**
     * User 数据装填
     * @param user
     * @return
     */
    public  User setUser(User user){
        //默认系统时间
        Date date = new Date();
        user.setPassword(TFM.md5(user.getPassword()));
        if (null==user.getRoles())
            user.setRoles("user");
        if (null==user.getAvatar())
            user.setAvatar("/");
        if (null==user.getCreatetime())
            user.setCreatetime(date);
        if (null==user.getUpdatetime())
            user.setUpdatetime(date);
        if (null==user.getActive())
            user.setActive("Y");
        return user;
    }
}
