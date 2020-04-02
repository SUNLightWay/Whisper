package com.example.myapplication.dao.DaoImpl;

import com.example.myapplication.dao.PlanListDao;
import com.example.myapplication.module.PlanListInfo;
import com.example.myapplication.module.SeatmateInfo;

import org.litepal.LitePal;

import java.util.List;

public class PlanListDaoImpl implements PlanListDao {

    @Override
    public List<PlanListInfo> findFirstLevelPlanList() {
        List<PlanListInfo> planListInfos = LitePal.select()
                .where("fatherplan = ?", "-1")
                .find(PlanListInfo.class);
        return planListInfos;
    }

    @Override
    public List<PlanListInfo> findChildPlanList(String fatherPlanId) {
        List<PlanListInfo> planListInfos = LitePal.select()
                .where("fatherplan = ?", fatherPlanId)
                .find(PlanListInfo.class);
        return planListInfos;
    }

    @Override
    public Boolean addPlan(PlanListInfo planListInfo) {
        return planListInfo.save();
    }

    @Override
    public Boolean updatePlanInfo(PlanListInfo planListInfo) {
        List<PlanListInfo> planListInfos = LitePal.select()
                .where("idplan = ?", planListInfo.getIdPlan())
                .find(PlanListInfo.class);
        if (planListInfos.size() == 0)
            return false;
        PlanListInfo plan = planListInfos.get(0);

        if (planListInfo.getEndTime() != null)
            plan.setEndTime(planListInfo.getEndTime());
        if (planListInfo.getCompletion() != 0)
            plan.setCompletion(planListInfo.getCompletion());
        if (planListInfo.getDetail() != null)
            plan.setDetail(planListInfo.getDetail());
        if (planListInfo.getGoal() != null)
            plan.setGoal(planListInfo.getGoal());
        if (planListInfo.getGoalType() != null)
            plan.setGoalType(planListInfo.getGoalType());
        if (planListInfo.getSignificance() != null)
            plan.setSignificance(planListInfo.getSignificance());
        if (planListInfo.getTitle() != null)
            plan.setTitle(planListInfo.getTitle());
        if (planListInfo.getBless() != null)
            plan.setBless(planListInfo.getBless());

        return plan.save();
    }

    @Override
    public Boolean deletePlan(String planId) {
        return (LitePal.deleteAll(PlanListInfo.class, "idplan = ?", planId) > 0 ? true: false);
    }

    @Override
    public List<PlanListInfo> findPlanById(String planId) {
        return (LitePal.select()
                .where("idplan = ?", planId)
                .find(PlanListInfo.class));
    }

    @Override
    public List<PlanListInfo> findLastPlanList(String userId) {

        return (LitePal.select()
                .where("iduser = ? and islast = ?", userId, "1")
                .find(PlanListInfo.class));
    }
}
