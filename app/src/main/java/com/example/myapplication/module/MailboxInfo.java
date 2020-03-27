package com.example.myapplication.module;

import com.example.myapplication.util.ConstUtil;

import java.util.Date;

public class MailboxInfo {

    Integer idMail = 0;
    String From = "";
    String To = "";
    Date sendTime = null;
    Date recelveTime = null;
    String title = "";
    Integer isPublic = ConstUtil.MailPublicType.TYPE_NOTPUBLIC;
    Integer isDelay = ConstUtil.MailDealyType.TYPE_NOTDELAY;

    public MailboxInfo(Integer idMail, String from, String to, Date sendTime, Date recelveTime, String title, Integer isPublic, Integer isDelay) {

        this.idMail = idMail;
        From = from;
        To = to;
        this.sendTime = sendTime;
        this.recelveTime = recelveTime;
        this.title = title;
        this.isPublic = isPublic;
        this.isDelay = isDelay;
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
}
