package com.example.myapplication.service;

import com.example.myapplication.module.PunchInfo;

import java.util.List;

public interface PunchService {

    /**
     * 增加打卡信息
     * @param punchInfo
     * @return
     */
    public Boolean addPunch(PunchInfo punchInfo);


    /**
     * 根据用户Id查询打卡信息
     * @param userId
     * @return
     */
    public List<PunchInfo> findPunchListMyUserId(String userId);


}
