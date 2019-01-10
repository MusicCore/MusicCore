package com.wjk.sstm.service.impl;

import com.wjk.sstm.dto.UserDto;
import com.wjk.sstm.mapper.UserMapper;
import com.wjk.sstm.model.User;
import com.wjk.sstm.service.UserService;
import com.wjk.sstm.until.TFM;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Service("SecurityService")
public class SecurityServiceImpl {

    @Resource(name = "userService")
    private UserService userService;
    @Autowired
    private TokenServiceImpl tokenService;

    public String createUserContext(String account,String password,HttpServletRequest request) throws Exception {
        if (account==null || password==null)
            throw new Exception("账号密码不为空");
        password = TFM.md5(password);
        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        User dto =  userService.selectByAccountAndPwd(user);
        checkAuth(dto);
        HttpSession session = request.getSession();
        String userAgent = request.getHeader("user-agent");
        String token = tokenService.generateToken(userAgent,account);
        tokenService.saveToken(account,token,session);
        UserDto dto1 = new UserDto();
        BeanUtils.copyProperties(dto,dto1);
        dto1.setToken(token);
        return token;
    }

    public void checkAuth(User dto) throws Exception{
        // 判断是否有登陆权限
        if (dto == null)
            throw new Exception("用户名、密码不正确");
    }

    public void deleteToken(String key){
        tokenService.removeToken(key);
    }
}
