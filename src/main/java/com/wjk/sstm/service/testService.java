package com.wjk.sstm.service;

import com.wjk.sstm.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.rmi.ServerException;

public interface testService {

    @Transactional(rollbackFor = Exception.class)
    public int insertUser(User user) throws Exception;
}
