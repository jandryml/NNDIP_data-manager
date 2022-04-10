package cz.edu.upce.fei.datamanager.data.service.impl;

import cz.edu.upce.fei.datamanager.data.entity.plan.LimitPlan;
import cz.edu.upce.fei.datamanager.data.repository.LimitPlanRepository;
import cz.edu.upce.fei.datamanager.data.service.LimitPlanService;
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

    @Override
    public List<LimitPlan> findAllLimitPlans() {
        return limitPlanRepository.findAll();
    }

    @Override
    public Optional<LimitPlan> findLimitPlanByName(String name) {
        return limitPlanRepository.findByName(name);
    }

    @Override
    public void saveLimitPlan(LimitPlan limitPlan) {
        // remove if same plan already exists
        deleteLimitPlan(limitPlan);
        limitPlanRepository.save(limitPlan);
    }

    @Override
    public void deleteLimitPlan(LimitPlan limitPlan) {
        limitPlanRepository.deleteByName(limitPlan.getName());
    }
}
