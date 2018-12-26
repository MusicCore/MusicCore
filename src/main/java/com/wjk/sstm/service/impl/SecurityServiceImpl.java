package com.wjk.sstm.service.impl;

import com.wjk.sstm.dto.UserContext;
import com.wjk.sstm.dto.UserDto;
import com.wjk.sstm.mapper.UserMapper;
import com.wjk.sstm.model.User;
import com.wjk.sstm.until.TFM;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Service("SecurityService")
public class SecurityServiceImpl {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TokenServiceImpl tokenService;

    public String createUserContext(String account,String password,HttpServletRequest request) throws Exception {
        if (account==null || password==null)
            throw new Exception("账号密码不为空");
        password = TFM.md5(password);
        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        User dto =  userMapper.selectByAccountAndPwd(user);
        checkAuth(dto);
        HttpSession session = request.getSession();
        session.setAttribute("loginAccount", account);
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
}
