package cz.edu.upce.fei.datamanager.data.service;

import cz.edu.upce.fei.datamanager.data.entity.plan.LimitPlan;

import java.util.List;

public interface LimitPlanService {

    List<LimitPlan> findAllLimitPlans();

    List<LimitPlan> findAllLimitPlans(String nameFilter);

    void saveLimitPlan(LimitPlan limitPlan);

    void deleteLimitPlan(LimitPlan limitPlan);
}
