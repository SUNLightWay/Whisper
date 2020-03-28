package com.example.myapplication.dao;

import com.example.myapplication.module.LoginInfo;
import com.example.myapplication.module.UserInfo;

public interface UserDao {

    /**
     * 按照id寻找login信息
     * @param idUser
     * @return
     */
    public LoginInfo findUserLoginById(String idUser);


    /**
     * 按照id返回用户所有信息
     * @param idUser
     * @return
     */
    public UserInfo findUserInfoById(String idUser);


    /**
     * 更新用户信息
     * @param userInfo
     * @return
     */
    public Boolean UpdateUser(UserInfo userInfo);
}
