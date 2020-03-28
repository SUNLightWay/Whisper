package com.example.myapplication.service;

import com.example.myapplication.module.LoginInfo;
import com.example.myapplication.module.UserInfo;

public interface UserService {

    /**
     * 注册用户
     * @return
     */
    public UserInfo doRegister(String phone, String password);

    /**
     * 登录
     * @param user
     * @return
     */
    public Boolean doLogin(LoginInfo user);


    /**
     * 查询用户列表
     * @return
     */
    public UserInfo findUserList();

    /**
     * 查询用户
     * @param idUser
     * @return
     */
    public UserInfo findUserByID(String idUser);

    /**
     * 修改密码
     * @param idUser
     * @param password
     * @return
     */
    public Boolean updatePassword(String idUser, String password);

    /**
     * 修改用户信息
     * @param userInfo
     * @return
     */
    public UserInfo updateUserInfo(UserInfo userInfo);
}
