package com.example.myapplication.module;

import com.example.myapplication.util.ConstUtil;

import org.litepal.crud.LitePalSupport;

public class UserInfo extends LitePalSupport {

    String idUser;
    String nickname;
    String phone;
    Integer rate;
    String idSeatmate; //同桌ID
    String idTeam;     //队伍ID
    String remark;     //备注
    Integer isPunch = ConstUtil.PlanPunchStatus.PLAN_NOT_ON_PUNCH;    //是否打卡
    byte[] headshot;    //头像

    public UserInfo() {
    }

    public UserInfo(String idUser, String nickname, String phone, Integer rate, String idSeatmate, String idTeam, String remark, Integer isPunch, byte[] headshot) {
        this.idUser = idUser;
        this.nickname = nickname;
        this.phone = phone;
        this.rate = rate;
        this.idSeatmate = idSeatmate;
        this.idTeam = idTeam;
        this.remark = remark;
        this.isPunch = isPunch;
        this.headshot = headshot;
    }

    public byte[] getHeadshot() {
        return headshot;
    }

    public void setHeadshot(byte[] headshot) {
        this.headshot = headshot;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getIdSeatmate() {
        return idSeatmate;
    }

    public void setIdSeatmate(String idSeatmate) {
        this.idSeatmate = idSeatmate;
    }

    public String getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(String idTeam) {
        this.idTeam = idTeam;
    }

    public Integer getIsPunch() {
        return isPunch;
    }

    public void setIsPunch(Integer isPunch) {
        this.isPunch = isPunch;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
