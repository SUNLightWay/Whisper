package com.example.myapplication.module;

import org.litepal.crud.LitePalSupport;

import java.sql.Time;
import java.util.Date;

public class NoteInfo extends LitePalSupport {

    Integer idNote = 0;
    Date time = null;
    String content = "";
    String idTeam = "";
    String author = "";

    public NoteInfo() {
    }

    public NoteInfo(Integer idNote, Date time, String content, String idTeam, String author) {
        this.idNote = idNote;
        this.time = time;
        this.content = content;
        this.idTeam = idTeam;
        this.author = author;
    }

    public Integer getIdNote() {
        return idNote;
    }

    public void setIdNote(Integer idNote) {
        this.idNote = idNote;
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
}
