package com.example.myapplication.dao.DaoImpl;

import android.util.Log;

import com.example.myapplication.dao.UserDao;
import com.example.myapplication.module.LoginInfo;
import com.example.myapplication.module.UserInfo;
import com.example.myapplication.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl implements UserDao {

    Connection connection;

    public UserDaoImpl(){
        connection = DBUtil.getConn();
    }

    @Override
    public LoginInfo findUserLoginById(String idUser) {
        try{
            String sql = "select * from login where ID_User = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, idUser);

            ResultSet rs = ps.executeQuery();
            if (rs != null){
                int count = rs.getMetaData().getColumnCount();
                Log.e("DBUtils", "列总数" + count);
                return new LoginInfo(rs.getString("ID_user"), rs.getString("Password"), rs.getString("Phone"));
            }
            else{
                return null;
            }
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }

    }

    @Override
    public UserInfo findUserInfoById(String idUser) {
        try{
            String sql = "select * from userinfo where ID_User = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, idUser);

            ResultSet rs = ps.executeQuery();
            if (rs != null){
                int count = rs.getMetaData().getColumnCount();
                Log.e("DBUtils", "列总数" + count);
                return new UserInfo(rs.getString("ID_User"), rs.getString("Nickname")
                    , rs.getString("Phone"), rs.getInt("Rate"), rs.getString("ID_Seatmate"),
                        rs.getString("ID_Team"), rs.getInt("isPunch"), rs.getString("Remark"));
            }
            else{
                return null;
            }
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean UpdateUser(UserInfo userInfo) {
        try{
            String sql = "update userinfo set";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, userInfo.getIdUser());

            ResultSet rs = ps.executeQuery();
            if (rs != null){
                int count = rs.getMetaData().getColumnCount();
                Log.e("DBUtils", "列总数" + count);
                /*return new UserInfo(rs.getString("ID_User"), rs.getString("Nickname")
                        , rs.getString("Phone"), rs.getInt("Rate"), rs.getString("ID_Seatmate"),
                        rs.getString("ID_Team"), rs.getInt("isPunch"), rs.getString("Remark"));*/
                return null;
            }
            else{
                return null;
            }
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}






















