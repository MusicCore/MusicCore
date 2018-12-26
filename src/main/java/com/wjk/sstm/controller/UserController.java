package com.wjk.sstm.controller;

import com.alibaba.fastjson.JSONObject;
import com.wjk.sstm.dto.UserDto;
import com.wjk.sstm.dto.dataDto;
import com.wjk.sstm.mapper.UserMapper;
import com.wjk.sstm.model.User;
import com.wjk.sstm.service.impl.SecurityServiceImpl;
import com.wjk.sstm.service.impl.TokenServiceImpl;
import com.wjk.sstm.until.Result;
import com.wjk.sstm.until.ResultFactory;
import com.wjk.sstm.until.TFM;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
//@RestController包含了@ResponseBody故返回的都是json类型的值，不会跳转jsp页面
public class UserController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SecurityServiceImpl securityService;

    private static Logger log = Logger.getLogger(UserController.class);

    /**
     * 登入
     * @param user
     * @param model
     * @return
     */
    @PostMapping(value = "/login")
    public Object login(User user, Model model,HttpServletRequest request){
        log.info("\n-------------------Method : login--------------------\n");
        try{
            String taken = securityService.createUserContext(user.getAccount(),user.getPassword(),request);
            JSONObject object = new JSONObject();
            object.put("taken",taken);
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
    public Object getUserInfo(){
        log.info("\n-------------------Method : login--------------------\n");
        try {
            User user = userMapper.getUser();
            JSONObject object = new JSONObject();
            object.put("name",user.getName());
            object.put("avatar",user.getAvatar());
            object.put("roles",user.getRoles());
            Result result=ResultFactory.buildSuccessResult(object);
            return result;
        }catch (Exception e){
            Result result=ResultFactory.buildFailResult(e.getMessage());
            return result;
        }
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
