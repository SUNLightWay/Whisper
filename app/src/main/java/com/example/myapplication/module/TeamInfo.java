package com.example.myapplication.module;


import java.util.Date;

public class TeamInfo {

    String idTeam = "";
    String captain = "";
    Integer number = 0;
    Integer numberLimit = 0;
    Date foundTime = new Date(2020, 2, 2);
    float ponchRate = 0;
    String inviteCode = "";
    String teamTitle = "";
    String teamInfo = "";

    public TeamInfo(String idTeam, String captain, Integer number, Integer numberLimit, Date foundTime, float ponchRate, String inviteCode, String teamTitle, String teamInfo) {
        this.idTeam = idTeam;
        this.captain = captain;
        this.number = number;
        this.numberLimit = numberLimit;
        this.foundTime = foundTime;
        this.ponchRate = ponchRate;
        this.inviteCode = inviteCode;
        this.teamTitle = teamTitle;
        this.teamInfo = teamInfo;
    }

    public String getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(String idTeam) {
        this.idTeam = idTeam;
    }

    public String getCaptain() {
        return captain;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getNumberLimit() {
        return numberLimit;
    }

    public void setNumberLimit(Integer numberLimit) {
        this.numberLimit = numberLimit;
    }

    public Date getFoundTime() {
        return foundTime;
    }

    public void setFoundTime(Date foundTime) {
        this.foundTime = foundTime;
    }

    public float getPonchRate() {
        return ponchRate;
    }

    public void setPonchRate(float ponchRate) {
        ponchRate = ponchRate;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getTeamTitle() {
        return teamTitle;
    }

    public void setTeamTitle(String teamTitle) {
        this.teamTitle = teamTitle;
    }

    public String getTeamInfo() {
        return teamInfo;
    }

    public void setTeamInfo(String teamInfo) {
        this.teamInfo = teamInfo;
    }
}
