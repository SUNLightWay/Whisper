package com.example.myapplication.dao;

import com.example.myapplication.module.LoginInfo;
import com.example.myapplication.module.UserInfo;

import java.util.List;

public interface UserDao {

    /**
     * 登录时根据信息查询用户信息
     * @param login
     * @return
     */
    public List<LoginInfo> findLoginInfo(LoginInfo login);


    /**
     * 添加用户
     * @param userInfo
     * @param password
     * @return
     */
    public Boolean IncreaseUser(UserInfo userInfo, String password);


    /**
     * 根据用户ID获取用户信息
     * @param userID
     * @return
     */
    public List<UserInfo> findUserInfoByID(String userID);


    /**
     * 查询用户列表
     * @return
     */
    public List<UserInfo> findUserList();

    /**
     * 更新密码
     * @param userId
     * @param password
     * @return
     */
    public Boolean updatePassword(String userId, String password);

    /**
     * 更新用户信息
     * @param userInfo
     * @return
     */
    public Boolean updateUserinfo(UserInfo userInfo);
}
