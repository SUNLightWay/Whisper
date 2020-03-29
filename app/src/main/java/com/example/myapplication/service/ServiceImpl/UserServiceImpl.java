package com.example.myapplication.service.ServiceImpl;

import com.example.myapplication.LoginActivity;
import com.example.myapplication.dao.DaoImpl.UserDaoImpl;
import com.example.myapplication.module.LoginInfo;
import com.example.myapplication.module.UserInfo;
import com.example.myapplication.service.UserService;
import com.example.myapplication.util.SPHelper;

public class UserServiceImpl implements UserService {

    UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public UserInfo doRegister(String phone, String password) {
        return null;
    }

    @Override
    public Boolean doLogin(LoginInfo user) {
        return null;
    }

    @Override
    public UserInfo findUserList() {
        return null;
    }

    @Override
    public UserInfo findUserByID(String idUser) {
        return null;
    }

    @Override
    public Boolean updatePassword(String idUser, String password) {
        return null;
    }

    @Override
    public UserInfo updateUserInfo(UserInfo userInfo) {
        return null;
    }
}
