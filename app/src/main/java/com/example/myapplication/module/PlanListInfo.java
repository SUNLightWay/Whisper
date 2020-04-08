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
    String bless;
    String significance;

    Integer type = ConstUtil.PlanType.TYPE_PERSONAL;
    Integer isLast = 0; //不为0时表示为最后一个
    Integer sumHourNeeded = 0;
    Integer isHoliday = ConstUtil.PlanHolidayStatus.PLAN_NOT_ON_HOLIDAY;
    Integer isPunch = ConstUtil.PlanPunchStatus.PLAN_NOT_ON_PUNCH;

    Date startTime;
    Date endTime;

    float completion = 0;
    float hourPerTime = 0;
    float hourRemained = 0;
    float hourPerDayAverage = 0;
    float shifting = 0; //偏移量

    public PlanListInfo() {
    }


    public PlanListInfo(String idPlan, String idUser, String fatherPlan, String title, String goal, String goalType, String detail, Date startTime, Date endTime, String significance, float completion, Integer type, String bless, Integer isLast, Integer sumHourNeeded, float hourPerTime) {
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
        this.isLast = isLast;
        this.sumHourNeeded = sumHourNeeded;
        this.hourPerTime = hourPerTime;
    }

    public Integer getIsPunch() {
        return isPunch;
    }

    public void setIsPunch(Integer isPunch) {
        this.isPunch = isPunch;
    }

    public Integer getIsHoliday() {
        return isHoliday;
    }

    public void setIsHoliday(Integer isHoliday) {
        this.isHoliday = isHoliday;
    }

    public float getShifting() {
        return shifting;
    }

    public void setShifting(float shifting) {
        this.shifting = shifting;
    }

    public float getHourRemained() {
        return hourRemained;
    }

    public void setHourRemained(float hourRemained) {
        this.hourRemained = hourRemained;
    }

    public float getHourPerDayAverage() {
        return hourPerDayAverage;
    }

    public void setHourPerDayAverage(float hourPerDayAverage) {
        this.hourPerDayAverage = hourPerDayAverage;
    }

    public float getHourPerTime() {
        return hourPerTime;
    }

    public void setHourPerTime(float hourPerTime) {
        this.hourPerTime = hourPerTime;
    }

    public Integer getIsLast() {
        return isLast;
    }

    public void setIsLast(Integer isLast) {
        this.isLast = isLast;
    }

    public Integer getSumHourNeeded() {
        return sumHourNeeded;
    }

    public void setSumHourNeeded(Integer sumHourNeeded) {
        this.sumHourNeeded = sumHourNeeded;
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
