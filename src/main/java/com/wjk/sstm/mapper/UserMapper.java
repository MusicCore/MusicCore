package com.wjk.sstm.mapper;

import com.wjk.sstm.dto.UserDto;
import com.wjk.sstm.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {

    public User selectByAccountAndPwd(User user);

    public int insert(User user);

    public int update(User user);

    public int selectIsHaveAccount(User user);

    public String selectPwdByAccount(User user);

    public User checkAP(String accout,String password);

    public List<User> getUserlist();

    public User getUser();
}
