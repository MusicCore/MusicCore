package com.wjk.sstm.service;

import com.wjk.sstm.dto.UserDto;
import com.wjk.sstm.model.User;

import java.util.List;

public interface UserService {
    /**
     * 插入
     * @param user
     * @return
     * @throws Exception
     */
    public int insertUser(User user) throws Exception;
    /**
     * 更新
     * @param user
     * @return
     * @throws Exception
     */
    public int updateUser(User user) throws Exception;

    /**
     * 拉取
     * @param account
     * @return
     * @throws Exception
     */
    public User listUserbyAccount(String account) throws Exception;

    /**
     * 列表
     * @param stratRow
     * @param endRow
     * @return
     * @throws Exception
     */
    public List<User> listUserbyAll(Integer stratRow, Integer endRow)throws Exception;

    /**
     * 验证账号密码
     * @param account
     * @param password
     * @return
     * @throws Exception
     */
    public User checkByAccountAndPwd(String account,String password) throws Exception;
    /**
     * 验证账号密码
     * @return
     * @throws Exception
     */
    public User selectByAccountAndPwd(User user) throws Exception;
    /**
     * 账号是否存在
     * @param user
     * @return
     * @throws Exception
     */
    public void checkIsHaveAccount(User user) throws Exception;

    /**
     *
     * @param
     * @return
     * @throws Exception
     */
    public UserDto selectUserById(String uid) throws Exception;
}
