package com.example.myapplication.module;

import org.litepal.crud.LitePalSupport;

import java.util.Date;

public class BulletinInfo extends LitePalSupport {

    Integer idButtetin = 0;
    String idTeam = "";
    String author = "";
    String title = "";
    Date time = null;
    String content = "";
    Integer clickNum = 0;
    Integer heart = 0;

    public BulletinInfo() {
    }

    public BulletinInfo(Integer idButtetin, String idTeam, String author, String title, Date time, String content, Integer clickNum, Integer heart) {
        this.idButtetin = idButtetin;
        this.idTeam = idTeam;
        this.author = author;
        this.title = title;
        this.time = time;
        this.content = content;
        this.clickNum = clickNum;
        this.heart = heart;
    }

    public Integer getIdButtetin() {
        return idButtetin;
    }

    public void setIdButtetin(Integer idButtetin) {
        this.idButtetin = idButtetin;
    }

    public String getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(String idTeam) {
        this.idTeam = idTeam;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getClickNum() {
        return clickNum;
    }

    public void setClickNum(Integer clickNum) {
        this.clickNum = clickNum;
    }

    public Integer getHeart() {
        return heart;
    }

    public void setHeart(Integer heart) {
        this.heart = heart;
    }
}
