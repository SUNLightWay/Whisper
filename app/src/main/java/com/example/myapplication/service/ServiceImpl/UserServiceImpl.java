package com.example.myapplication.service.ServiceImpl;

import android.util.Log;

import com.example.myapplication.LoginActivity;
import com.example.myapplication.dao.DaoImpl.UserDaoImpl;
import com.example.myapplication.module.LoginInfo;
import com.example.myapplication.module.UserInfo;
import com.example.myapplication.service.UserService;
import com.example.myapplication.util.SPHelper;

import org.litepal.LitePal;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final String TAG = "UserServiceImpl";
    UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public Boolean doRegister(UserInfo userInfo, String password) {

        if (userDao.IncreaseUser(userInfo, password)){
            Log.d(TAG, "doRegister: true");
            return true;
        }
        return false;
    }

    @Override
    public Boolean doLogin(LoginInfo user) {
        List<LoginInfo> login = userDao.findLoginInfo(user);
        if (login.size() > 0 && login.get(0).getPassword().equals(user.getPassword())){
            Log.d(TAG, "doLogin: successful. userID:" + user.getIdUser());
            return true;
        }
        Log.d(TAG, "doLogin: fail");
        return false;
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
