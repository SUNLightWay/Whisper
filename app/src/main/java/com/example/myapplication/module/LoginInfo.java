package com.example.myapplication.module;

import org.litepal.crud.LitePalSupport;

public class LoginInfo extends LitePalSupport {

    String idUser = "";
    String password = "";
    String phone = "";

    public LoginInfo() {
    }

    public LoginInfo(String idUser, String password, String phone) {
        this.idUser = idUser;
        this.password = password;
        this.phone = phone;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
