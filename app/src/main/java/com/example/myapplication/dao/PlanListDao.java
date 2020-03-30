package com.example.myapplication.dao;

import com.example.myapplication.module.PlanListInfo;

import java.util.List;

public interface PlanListDao {


    /**
     * 查询第一级计划
     * @return
     */
    public List<PlanListInfo> findFirstLevelPlanList();


    /**
     * 查询子计划列表
     * @return
     */
    public List<PlanListInfo> findChildPlanList(String fatherPlanId);


    /**
     * 新增计划
     * @param planListInfo
     * @return
     */
    public Boolean addPlan(PlanListInfo planListInfo);


    /**
     * 修改计划
     * @param planListInfo
     * @return
     */
    public Boolean updatePlanInfo(PlanListInfo planListInfo);


    /**
     * 删除计划
     * @param planId
     * @return
     */
    public Boolean deletePlan(String planId);
}
