package cz.edu.upce.fei.datamanager.data.service;

import cz.edu.upce.fei.datamanager.data.entity.plan.LimitPlan;

import java.util.List;
import java.util.Optional;

public interface LimitPlanService {

    List<LimitPlan> findAllLimitPlans();

    Optional<LimitPlan> findLimitPlanByName(String name);

    void saveLimitPlan(LimitPlan limitPlan);

    void deleteLimitPlan(LimitPlan limitPlan);
}
