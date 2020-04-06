package com.example.myapplication.dao.DaoImpl;

import android.util.Log;

import com.example.myapplication.dao.UserDao;
import com.example.myapplication.module.LoginInfo;
import com.example.myapplication.module.UserInfo;
import com.example.myapplication.util.DBUtil;

import org.litepal.LitePal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private final String TAG = "UserDaoImpl";

    @Override
    public List<LoginInfo> findLoginInfo(LoginInfo user) {
        List<LoginInfo> login = LitePal.select("iduser", "password", "phone")
                .where("iduser = ? or phone = ?", user.getIdUser(), user.getIdUser())
                .limit(1)
                .find(LoginInfo.class);
        Log.d(TAG, "findLoginInfo: size() = " + login.size());
        return login;
    }

    @Override
    public Boolean IncreaseUser(UserInfo userInfo, String password) {
        //同时更新loginInfo表
        LoginInfo login = new LoginInfo();
        login.setPhone(userInfo.getPhone());
        login.setIdUser(userInfo.getIdUser());
        login.setPassword(password);

        if (userInfo.save() && login.save())
            return true;
        return false;
    }

    @Override
    public List<UserInfo> findUserInfoByID(String userID) {
        List<UserInfo> login = LitePal.select()
                .where("iduser = ?", userID)
                .limit(1)
                .find(UserInfo.class);
        return login;
    }

    @Override
    public List<UserInfo> findUserList() {
        List<UserInfo> userInfos = LitePal.findAll(UserInfo.class);
        return userInfos;
    }

    @Override
    public Boolean updatePassword(String userId, String password) {
        List<LoginInfo> loginInfos = LitePal.select()
                .where("iduser = ?", userId)
                .limit(1)
                .find(LoginInfo.class);
        LoginInfo login = loginInfos.get(0);
        login.setPassword(password);
        if (login.save()) {
            Log.d(TAG, "updatePassword: true");
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateUserinfo(UserInfo userInfo) {

        List<UserInfo> userInfos = LitePal.select()
                .where("iduser = ?", userInfo.getIdUser())
                .limit(1)
                .find(UserInfo.class);
        if (userInfos.size() == 0){
            Log.d(TAG, "updateUserinfo: user not exists");
            return  false;
        }
        UserInfo user = userInfos.get(0);
        if(userInfo.getIdTeam() != null)
            user.setIdTeam(userInfo.getIdTeam());
        if(userInfo.getIdSeatmate() != null)
            user.setIdSeatmate(userInfo.getIdSeatmate());
        if(userInfo.getIsPunch() != null)
            user.setIsPunch(userInfo.getIsPunch());
        if(userInfo.getNickname() != null)
            user.setNickname(userInfo.getNickname());
        if(userInfo.getPhone() != null){
            user.setPhone(userInfo.getPhone());
            LoginInfo loginInfo = LitePal.select()
                    .where("iduser = ?", userInfo.getIdUser())
                    .limit(1)
                    .find(LoginInfo.class).get(0);
            loginInfo.setPhone(userInfo.getPhone());
            loginInfo.save();
        }
        if(userInfo.getRate() != null)
            user.setRate(userInfo.getRate());
        if(userInfo.getRemark() != null)
            user.setRemark(userInfo.getRemark());
        if(userInfo.getHeadshot() != null)
            user.setHeadshot(userInfo.getHeadshot());
        if(user.save()){
            return true;
        }
        return false;
    }
}






















