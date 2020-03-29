package com.example.myapplication.dao;

import com.example.myapplication.module.SeatmateInfo;

import java.util.Date;
import java.util.List;

public interface SeatmateDao {

    /**
     * 根据同桌id查找同桌信息
     * @param seatmateId
     * @return
     */
    public List<SeatmateInfo> findSeatmateBySeatmateId(String seatmateId);

    /**
     * 根据用户id查找与之相关的同桌
     * @param userId
     * @return
     */
    public List<SeatmateInfo> findSeatmateByUserId(String userId);


    /**
     * 根据用户id和同桌状态查找，包括但不限于：等待对方同意的条目、失败的条目
     * @param userId
     * @param status
     * @return
     */
    public List<SeatmateInfo> findSeatmateByUserIdAndStatus(String userId, int status);

    /**
     * 仅以person1为依据的查询
     * @param userId
     * @param status
     * @return
     */
    public List<SeatmateInfo> findSeatmateByUserIdAndStatusLimitPerson1(String userId, int status);


    /**
     * 仅以person2为依据的查询
     * @param userId
     * @param status
     * @return
     */
    public List<SeatmateInfo> findSeatmateByUserIdAndStatusLimitPerson2(String userId, int status);

    /**
     * 创建同桌
     * @param seatmate
     * @return
     */
    public Boolean addSeatmate(SeatmateInfo seatmate);


    /**
     * 修改同桌信息
     * @param status
     * @return
     */
    public Boolean updateSeatemate(String seatmateId, int status, Date startTime);


    /**
     * 仅修改用户状态，包括但不限于：任务成功、失败
     * @param seatmateId
     * @param status
     * @return
     */
    public Boolean updateSeatmateStatusOnly(String seatmateId, int status);
}
