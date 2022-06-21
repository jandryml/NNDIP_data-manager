package cz.edu.upce.fei.datamanager.data.service.plan;

import cz.edu.upce.fei.datamanager.data.entity.plan.limit.LimitPlan;
import cz.edu.upce.fei.datamanager.data.entity.plan.limit.YearPeriod;
import cz.edu.upce.fei.datamanager.data.entity.plan.limit.YearPeriodType;

import java.util.List;
import java.util.Optional;

public interface LimitPlanService {

    List<LimitPlan> findAllLimitPlans();

    Optional<LimitPlan> findLimitPlanByName(String name, YearPeriodType periodType);

    YearPeriodType getActiveYearPeriod();

    void setActiveYearPeriod(YearPeriodType yearPeriod);

    void saveLimitPlan(LimitPlan limitPlan, YearPeriodType periodType);

    void deleteLimitPlan(LimitPlan limitPlan, YearPeriod yearPeriod);
}
