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
}
