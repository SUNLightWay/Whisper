package com.example.myapplication.module;

import com.example.myapplication.util.ConstUtil;

import org.litepal.crud.LitePalSupport;

import java.util.Date;

public class PlanListInfo extends LitePalSupport {

    Integer idPlan = 0;
    String idUser = "";
    Integer fatherPlan = -1;
    String title = "";
    String detail = "";
    Date startTime = null;
    Date endTime = null;
    String significance = "";
    float completion = 0;
    Integer type = ConstUtil.PlanType.TYPE_PERSONAL;

    public PlanListInfo() {
    }

    public PlanListInfo(Integer idPlan, String idUser, Integer fatherPlan, String title, String detail, Date startTime, Date endTime, String significance, float completion, Integer type) {

        this.idPlan = idPlan;
        this.idUser = idUser;
        this.fatherPlan = fatherPlan;
        this.title = title;
        this.detail = detail;
        this.startTime = startTime;
        this.endTime = endTime;
        this.significance = significance;
        this.completion = completion;
        this.type = type;
    }

    public Integer getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Integer idPlan) {
        this.idPlan = idPlan;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public Integer getFatherPlan() {
        return fatherPlan;
    }

    public void setFatherPlan(Integer fatherPlan) {
        this.fatherPlan = fatherPlan;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getSignificance() {
        return significance;
    }

    public void setSignificance(String significance) {
        this.significance = significance;
    }

    public float getCompletion() {
        return completion;
    }

    public void setCompletion(float completion) {
        this.completion = completion;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
