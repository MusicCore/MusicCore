package com.wjk.sstm.service.impl;

import com.wjk.sstm.mapper.UserMapper;
import com.wjk.sstm.model.User;
import com.wjk.sstm.service.testService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("testService")
public class testServiceImpl implements testService {
    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertUser(User user)throws Exception {
        userMapper.insert(user);
        throw new Exception("通讯录分组id不存在");
    }
}
