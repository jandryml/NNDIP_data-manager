package cz.edu.upce.fei.datamanager.data.service.plan.impl;

import cz.edu.upce.fei.datamanager.data.entity.plan.limit.LimitPlan;
import cz.edu.upce.fei.datamanager.data.entity.plan.limit.YearPeriod;
import cz.edu.upce.fei.datamanager.data.entity.plan.limit.YearPeriodType;
import cz.edu.upce.fei.datamanager.data.repository.plan.LimitPlanRepository;
import cz.edu.upce.fei.datamanager.data.repository.plan.YearPeriodRepository;
import cz.edu.upce.fei.datamanager.data.service.plan.LimitPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LimitPlanServiceImpl implements LimitPlanService {

    private final LimitPlanRepository limitPlanRepository;
    private final YearPeriodRepository yearPeriodRepository;


    @Override
    public List<LimitPlan> findAllLimitPlans() {
        return limitPlanRepository.findAll();
    }

    @Override
    public Optional<LimitPlan> findLimitPlanByName(String name, YearPeriodType periodType) {
        YearPeriod yearPeriod = yearPeriodRepository.findByName(periodType);
        return limitPlanRepository.findByNameAndYearPeriod(name, yearPeriod);
    }

    @Override
    public YearPeriodType getActiveYearPeriod() {
        return yearPeriodRepository.getActive().getName();
    }

    @Override
    public void setActiveYearPeriod(YearPeriodType yearPeriod) {
        yearPeriodRepository.setAllToNotActive();
        yearPeriodRepository.setOneToActive(yearPeriod);
    }

    @Override
    public void saveLimitPlan(LimitPlan limitPlan, YearPeriodType periodType) {
        YearPeriod yearPeriod = yearPeriodRepository.findByName(periodType);
        limitPlan.setYearPeriod(yearPeriod);
        // remove if same plan already exists
        deleteLimitPlan(limitPlan, yearPeriod);
        limitPlanRepository.save(limitPlan);
    }

    @Override
    public void deleteLimitPlan(LimitPlan limitPlan, YearPeriod yearPeriod) {
        limitPlanRepository.deleteByNameAndYearPeriod(limitPlan.getName(), yearPeriod);
    }
}
