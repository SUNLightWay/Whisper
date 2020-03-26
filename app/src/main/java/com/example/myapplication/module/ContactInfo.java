package com.example.myapplication.module;

public class ContactInfo {

    protected String text;

    protected int imgId;

    public ContactInfo(String text,int imgId){
        this.text = text;
        this.imgId = imgId;
    }

    public String getText(){
        return text;
    }

    public int getImgId() {
        return imgId;
    }
}
