package com.example.myapplication.module;

public class ContactInfo {

    protected String title;
    protected Integer timeRemain;
    protected String significance;
    protected Integer remainDays;
    protected int imgId;

    public ContactInfo(String title, Integer timeRemain, String significance, int imgId, int remainDays) {
        this.title = title;
        this.timeRemain = timeRemain;
        this.significance = significance;
        this.imgId = imgId;
        this.remainDays = remainDays;
    }

    public Integer getRemainDays() {
        return remainDays;
    }

    public void setRemainDays(Integer remainDays) {
        this.remainDays = remainDays;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTimeRemain() {
        return timeRemain;
    }

    public void setTimeRemain(Integer timeRemain) {
        this.timeRemain = timeRemain;
    }

    public String getSignificance() {
        return significance;
    }

    public void setSignificance(String significance) {
        this.significance = significance;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
