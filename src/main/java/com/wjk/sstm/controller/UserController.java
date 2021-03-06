package com.wjk.sstm.controller;

import com.alibaba.fastjson.JSONObject;
import com.wjk.sstm.dto.UserDto;
import com.wjk.sstm.mapper.UserMapper;
import com.wjk.sstm.model.CommonContext;
import com.wjk.sstm.model.User;
import com.wjk.sstm.service.UserService;
import com.wjk.sstm.service.impl.SecurityServiceImpl;
import com.wjk.sstm.until.Result;
import com.wjk.sstm.until.ResultFactory;
import com.wjk.sstm.until.TFM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 用户功能
 * wjk
 */

@RestController
@RequestMapping("/api")
//@RestController包含了@ResponseBody故返回的都是json类型的值，不会跳转jsp页面
public class UserController {
    @Autowired
    private SecurityServiceImpl securityService;

    @Resource(name = "userService")
    private UserService userService;

    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    /**
     * 查看用户信息(全)
     * @return
     */
    @PostMapping(value = "/userlist")
    public Object getUserList(@RequestParam Integer pageSize,@RequestParam Integer currentPage){
        log.info("\n-------------------Method : login--------------------\n");
        try {
            Integer stratRow = pageSize*(currentPage-1);
            List<User> list = userService.listUserbyAll(stratRow,pageSize);
            JSONObject object = new JSONObject();
            object.put("items",list);
            Result result=ResultFactory.buildSuccessResult(object);
            return result;
        }catch (Exception e){
            log.debug(e.getMessage());
            Result result=ResultFactory.buildFailResult(e.getMessage());
            return result;
        }
    }
    /**
     * 拉取用户信息
     * @param account
     * @return
     */
    @PostMapping(value = "/info")
    public Object getUserInfo(@RequestParam String account){
        log.info("\n-------------------Method : 取得"+account+"信息--------------------\n");
        try {
            User user = userService.listUserbyAccount(account);
            JSONObject object = new JSONObject();
            object.put("name",CommonContext.getInstance().getName());
            object.put("avatar",CommonContext.getInstance().getAvatar());
            String [] roles = {CommonContext.getInstance().getRoles()};
            object.put("roles",roles);//返回数组格式权限
            Result result=ResultFactory.buildSuccessResult(object);
            return result;
        }catch (Exception e){
            log.debug(e.getMessage());
            Result result=ResultFactory.buildFailResult(e.getMessage());
            return result;
        }
    }
    /**
     * 注册
     * @param user
     * @param
     * @param request
     * @return
     */
    @PostMapping(value = "/register")
    public Object register(User user,HttpServletRequest request){
        log.info("\n-------------------Method : register--------------------\n");
        try {
            userService.checkIsHaveAccount(user);
            Result result=ResultFactory.buildFailResult("账号已存在");
            return result;
        }catch (Exception e){
            try {
                setUser(user);
                userService.insertUser(user);
//                userMapper.insert(user);
                Result result=ResultFactory.buildFailResult("注册成功");
                return result;
            }catch (Exception e1){
                log.debug(e1.getMessage());
                Result result=ResultFactory.buildFailResult(e1.getMessage());
                return result;
            }
        }
    }
    /**
     * 更新
     * @param user
     * @return
     */
    @PostMapping(value = "/userupdate")
    public Result updateInfo(@RequestBody User user){
        log.info("\n-------------------Method : register--------------------\n");
        try{
            userService.updateUser(user);
        }catch (Exception e){
            log.info(e.getMessage());
            Result result=ResultFactory.buildFailResult(e.getMessage());
        }
        return ResultFactory.buildSuccessResult("更新用户信息成功");
    }
    /**
     * 修改密码
     * @return
     */
    @PostMapping(value = "/updatePwd")
    public Result updatePassword(@RequestBody UserDto userDto){
        log.info("\n-------------------Method : getLostPassword--------------------\n");
        try{
            String oldPwd  = TFM.md5(userDto.getOldPassword());
            User user = userService.checkByAccountAndPwd(userDto.getAccount(),oldPwd);
            if (null != user){
                String newPwd = TFM.md5(userDto.getNewPassword());
                user.setPassword(newPwd);
                userService.updateUser(user);
            }
            securityService.deleteToken(userDto.getToken());
        }catch (Exception se){
//            在命令行打印异常信息在程序中出错的位置及原因            se.printStackTrace();
            log.debug(se.getMessage());
            return ResultFactory.buildFailResult("修改密码失败");
        }
        return ResultFactory.buildSuccessResult("修改密码成功，请重新登入");
    }
    /**
     * User 数据装填
     * @param user
     * @return
     */
    private  User setUser(User user){
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
