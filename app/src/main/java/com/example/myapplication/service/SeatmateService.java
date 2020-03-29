package com.example.myapplication.service;

import com.example.myapplication.dao.SeatmateDao;
import com.example.myapplication.module.SeatmateInfo;
import com.example.myapplication.module.UserInfo;

import java.util.List;

public interface SeatmateService {


    /**
     * 发起请求
     * @param seatmateInfo
     * @return
     */
    public Boolean sendRequest(SeatmateInfo seatmateInfo);


    /**
     * 答复请求
     * @param seatmateId
     * @param reply （type详见ConstUtil）
     * @return
     */
    public Boolean replyRequest(String seatmateId, int reply);


    /**
     * 查找用户需要答复的同桌请求
     * @param userId
     * @return
     */
    public List<SeatmateInfo> findSeatmateNeedToResponse(String userId);


    /**
     * 查找用户已发起但对方未回复的同桌请求
     * @param userId
     * @return
     */
    public List<SeatmateInfo> findSeatmateWaitingForAnotherResponse(String userId);


    /**
     * 查询失败或成功的同桌计划
     * @param userId
     * @return
     */
    public List<SeatmateInfo> findSeatmateFailedorSucceeded(String userId);


    /**
     * 查询正在进行的同桌计划
     * @param userId
     * @return
     */
    public List<SeatmateInfo> findSeatmateProcessing(String userId);
}
