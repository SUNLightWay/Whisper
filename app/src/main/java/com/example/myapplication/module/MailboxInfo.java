package com.example.myapplication.module;

import com.example.myapplication.util.ConstUtil;

import org.litepal.crud.LitePalSupport;

import java.util.Date;

public class MailboxInfo extends LitePalSupport {

    Integer idMail = 0;
    String From = "";
    String To = "";
    Date sendTime = null;
    Date recelveTime = null;
    String title = "";
    String content = "";
    Integer isPublic = ConstUtil.MailPublicType.TYPE_NOTPUBLIC;
    Integer isDelay = ConstUtil.MailDealyType.TYPE_NOTDELAY;
    Integer status = ConstUtil.MailSendStatus.UNREACHED;

    public MailboxInfo() {
    }


    public MailboxInfo(Integer idMail, String from, String to, Integer isPublic, Integer isDelay ,
                       Date sendTime, Date recelveTime, String title, String content,Integer status) {

        this.idMail = idMail;
        From = from;
        To = to;
        this.sendTime = sendTime;
        this.recelveTime = recelveTime;
        this.title = title;
        this.isPublic = isPublic;
        this.isDelay = isDelay;
        this.content = content;
        this.status = status;
    }


    public Integer getIdMail() {
        return idMail;
    }

    public void setIdMail(Integer idMail) {
        this.idMail = idMail;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String to) {
        To = to;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getRecelveTime() {
        return recelveTime;
    }

    public void setRecelveTime(Date recelveTime) {
        this.recelveTime = recelveTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }

    public Integer getIsDelay() {
        return isDelay;
    }

    public void setIsDelay(Integer isDelay) {
        this.isDelay = isDelay;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MailboxInfo{" +
                "idMail=" + idMail +
                ", From='" + From + '\'' +
                ", To='" + To + '\'' +
                ", sendTime=" + sendTime +
                ", recelveTime=" + recelveTime +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", isPublic=" + isPublic +
                ", isDelay=" + isDelay +
                '}';
    }
}
