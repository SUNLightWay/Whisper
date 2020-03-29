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

    @Override
    public List<LoginInfo> findLoginInfo(LoginInfo user) {
        List<LoginInfo> login = LitePal.select("iduser", "password", "phone")
                .where("iduser = ? or phone = ?", user.getIdUser(), user.getIdUser())
                .limit(1)
                .find(LoginInfo.class);
        return login;
    }

    @Override
    public Boolean IncreaseUser(UserInfo userInfo, String password) {
        //同时更新loginInfo表
        LoginInfo login = new LoginInfo();
        login.setPhone(userInfo.getPhone());
        login.setIdUser(userInfo.getIdUser());
        login.setPhone(password);

        if (userInfo.save() && login.save())
            return true;
        return false;
    }
}






















