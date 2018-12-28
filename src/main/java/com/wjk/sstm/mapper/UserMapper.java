package com.wjk.sstm.mapper;

import com.wjk.sstm.dto.UserDto;
import com.wjk.sstm.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.rmi.ServerException;
import java.util.List;

@Repository
@Mapper
public interface UserMapper {

    public User selectByAccountAndPwd(User user) throws ServerException;

    public User checkByAccountAndPwd(String account,String password) throws ServerException;

    public int insert(User user) throws ServerException;

    public int update(User user) throws ServerException;

    public int selectIsHaveAccount(User user) throws ServerException;

    public String selectPwdByAccount(User user) throws ServerException;

    public User checkAP(String account,String password) throws ServerException;

    public List<User> getUserlist() throws ServerException;

    public User getUser(@Param("account") String account) throws ServerException;
}
