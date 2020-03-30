package com.example.myapplication.module;

import com.example.myapplication.util.ConstUtil;

import org.litepal.crud.LitePalSupport;

import java.sql.Time;
import java.util.Date;

public class PlanListInfo extends LitePalSupport {

    String idPlan;
    String idUser;
    String fatherPlan;
    String title;
    String goal;
    String goalType;
    String detail;
    Date startTime;
    Date endTime;
    String significance;
    float completion;
    Integer type = ConstUtil.PlanType.TYPE_PERSONAL;
    String bless;
    Integer isLast;


    public PlanListInfo() {
    }

    public PlanListInfo(String idPlan, String idUser, String fatherPlan, String title, String goal, String goalType, String detail, Date startTime, Date endTime, String significance, float completion, Integer type, String bless) {
        this.idPlan = idPlan;
        this.idUser = idUser;
        this.fatherPlan = fatherPlan;
        this.title = title;
        this.goal = goal;
        this.goalType = goalType;
        this.detail = detail;
        this.startTime = startTime;
        this.endTime = endTime;
        this.significance = significance;
        this.completion = completion;
        this.type = type;
        this.bless = bless;
    }

    public String getBless() {
        return bless;
    }

    public void setBless(String bless) {
        this.bless = bless;
    }

    public String getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(String idPlan) {
        this.idPlan = idPlan;
    }

    public String getFatherPlan() {
        return fatherPlan;
    }

    public void setFatherPlan(String fatherPlan) {
        this.fatherPlan = fatherPlan;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getGoalType() {
        return goalType;
    }

    public void setGoalType(String goalType) {
        this.goalType = goalType;
    }


    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
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
