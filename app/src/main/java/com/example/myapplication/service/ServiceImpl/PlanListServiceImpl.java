package com.example.myapplication.service.ServiceImpl;

import com.example.myapplication.dao.DaoImpl.PlanListDaoImpl;
import com.example.myapplication.module.PlanListInfo;
import com.example.myapplication.service.PlanListService;

import java.util.List;

public class PlanListServiceImpl implements PlanListService {

    private PlanListDaoImpl planListDao = new PlanListDaoImpl();
    private final String TAG = "PlanListServiceImpl";

    @Override
    public List<PlanListInfo> findFirstLevelPlanList(String userId) {
        return planListDao.findFirstLevelPlanList(userId);
    }

    @Override
    public List<PlanListInfo> findChildPlanList(String fatherPlanId) {
        return planListDao.findChildPlanList(fatherPlanId);
    }

    @Override
    public Boolean addPlan(PlanListInfo planListInfo) {
        return planListDao.addPlan(planListInfo);
    }

    @Override
    public Boolean updatePlanInfo(PlanListInfo planListInfo) {
        return planListDao.updatePlanInfo(planListInfo);
    }

    @Override
    public Boolean deletePlan(String planId) {
        return planListDao.deletePlan(planId);
    }

    @Override
    public PlanListInfo findPlanById(String planId) {
        return planListDao.findPlanById(planId).get(0);
    }

    @Override
    public List<PlanListInfo> findLastPlanList(String userId) {
        return planListDao.findLastPlanList(userId);
    }

    @Override
    public List<PlanListInfo> findPunchedPlanList(String userId) {
        return planListDao.findPunchedPlanList(userId);
    }
}
