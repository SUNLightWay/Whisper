package com.example.myapplication.module;

import org.litepal.crud.LitePalSupport;

public class RankingInfo extends LitePalSupport {
    float score;//当前评分
    String feelings;//感受

    public RankingInfo(){

    }

    public RankingInfo(float score,String feelings){
        this.feelings=feelings;
        this.score=score;
    }

    public float getScore(){
        return score;
    }

    public void setScore(float score){
        this.score=score;
    }

    public String getFeelings(){
        return feelings;
    }

    public void setFeelings(String feelings){
        this.feelings=feelings;
    }
}
