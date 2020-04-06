package com.example.myapplication.module;

import android.content.Intent;

import org.litepal.crud.LitePalSupport;

import java.util.Date;

public class PunchInfo extends LitePalSupport {

    private String idPunch;
    private String idUser;
    private Date time;
    private Integer star;
    private float planCompletion;
    private String summary;

    public PunchInfo() {
    }

    public PunchInfo(String idPunch, String idUser, Date time, Integer star, float planCompletion, String summary) {
        this.idPunch = idPunch;
        this.idUser = idUser;
        this.time = time;
        this.star = star;
        this.planCompletion = planCompletion;
        this.summary = summary;
    }

    public String getIdPunch() {
        return idPunch;
    }

    public void setIdPunch(String idPunch) {
        this.idPunch = idPunch;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public float getPlanCompletion() {
        return planCompletion;
    }

    public void setPlanCompletion(float planCompletion) {
        this.planCompletion = planCompletion;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
