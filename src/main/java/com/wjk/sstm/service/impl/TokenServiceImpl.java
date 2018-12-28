package com.wjk.sstm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wjk.sstm.dto.UserDto;
import com.wjk.sstm.model.User;
import com.wjk.sstm.until.RedisUtils;
import com.wjk.sstm.until.TFM;
import nl.bitwalker.useragentutils.UserAgent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service("tokenService")
public class TokenServiceImpl {
//    @Resource
    private RedisUtils redisUtil;
    public TokenServiceImpl(){
        init();
    }

    private void init() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("config/applicationContext-redis.xml");
        redisUtil = (RedisUtils) applicationContext.getBean("redisUtil");
    }

    /**
     * 生成token
     * @param userAgent
     * @param account
     * @return
     */
    public String generateToken(String userAgent,String account){
        StringBuffer token =  new StringBuffer();
        //加token:
        token.append("token:");
        //加设备
        UserAgent userAgent1 = UserAgent.parseUserAgentString(userAgent);
        if (userAgent1.getOperatingSystem().isMobileDevice()){
            token.append("MOBILE-");
        }else{
            token.append("PC-");
        }
//        用户名
        token.append(TFM.md5(account)+"-");
//        时间
        token.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"-");
//        随机数
        token.append(new Random().nextInt((999999 - 111111 + 1)) + 111111);
        return token.toString();
    }

    public void saveToken(String key,String token,HttpSession session){
        if (token.startsWith("token:PC")){
            //电脑端生成令牌过期时间为20分钟
            redisUtil.set(token,key,20,TimeUnit.MINUTES);
            redisUtil.set(key,session,20,TimeUnit.MINUTES);
        }else{
            redisUtil.set(token,key);
            redisUtil.set(key,session);
        }
    }

    public void removeToken(String key){
        Object sk = redisUtil.get(key);
        redisUtil.delete(key);
        redisUtil.delete(sk.toString());
    }


}
