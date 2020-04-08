package com.example.myapplication.service.ServiceImpl;

import android.util.Log;

import com.example.myapplication.LoginActivity;
import com.example.myapplication.dao.DaoImpl.UserDaoImpl;
import com.example.myapplication.module.LoginInfo;
import com.example.myapplication.module.UserInfo;
import com.example.myapplication.service.UserService;
import com.example.myapplication.util.MD5Util;
import com.example.myapplication.util.SPHelper;

import org.litepal.LitePal;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final String TAG = "UserServiceImpl";
    UserDaoImpl userDao = new UserDaoImpl();
    MD5Util md5Util = new MD5Util();

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
        Log.d(TAG, "doLogin: " + user.getIdUser() + " " + user.getPassword() + " "+ login.get(0).getIdUser() + " " + login.get(0).getPassword());
        if (login.size() > 0 && login.get(0).getPassword().equals(user.getPassword())){
            Log.d(TAG, "doLogin: successful. userID:" + user.getIdUser());
            return true;
        }
        Log.d(TAG, "doLogin: fail");
        return false;
    }

    @Override
    public List<UserInfo> findUserList() {
        List<UserInfo> userInfos = userDao.findUserList();
        Log.d(TAG, "findUserList: " + userInfos.size());
        return userInfos;
    }

    @Override
    public UserInfo findUserByID(String idUser) {

        List<UserInfo> userInfos = userDao.findUserInfoByID(idUser);
        if(userInfos.size() > 0){
            Log.d(TAG, "findUserByID: " + userInfos.get(0).getIdUser());
            return userInfos.get(0);
        }
        return null;
    }

    @Override
    public Boolean updatePassword(String idUser, String password) {
        return userDao.updatePassword(idUser, password);
    }

    @Override
    public Boolean updateUserInfo(UserInfo userInfo) {
        return userDao.updateUserinfo(userInfo);
    }

    //反馈
    @Override
    public Boolean updateFeedbackInfo(String problem,String details,String contact){
        return userDao.updateFeedbackinfo(problem,details,contact);
    }

    //个人评级
    @Override
    public Boolean updateRankingInfo(float score,String feelings){
        return userDao.updateRankinginfo(score,feelings);
    }

}
