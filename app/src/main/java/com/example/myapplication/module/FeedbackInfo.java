package com.example.myapplication.module;

import org.litepal.crud.LitePalSupport;

public class FeedbackInfo extends LitePalSupport {
    String problem=""; //问题名称
    String details=""; //详细问题
    String contact="";//联系方式

    //String UserID="";//反馈的用户名

    public FeedbackInfo(){

    }

    public FeedbackInfo(String problem,String details,String contact){
        this.problem=problem;
        this.details=details;
        this.contact=contact;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
