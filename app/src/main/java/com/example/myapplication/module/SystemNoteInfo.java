package com.example.myapplication.module;

import org.litepal.crud.LitePalSupport;

import java.util.Date;

public class SystemNoteInfo extends LitePalSupport {

    private String idNote;
    private String title;
    private String to;
    private Date time = null;
    private String content;
    private Integer type;
    private Integer isRead;

    public SystemNoteInfo() {
    }

    public SystemNoteInfo(String idNote, String title, String to, Date time, String content, Integer type, Integer isRead) {
        this.idNote = idNote;
        this.title = title;
        this.to = to;
        this.time = time;
        this.content = content;
        this.type = type;
        this.isRead = isRead;
    }

    public String getIdNote() {
        return idNote;
    }

    public void setIdNote(String idNote) {
        this.idNote = idNote;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }
}
