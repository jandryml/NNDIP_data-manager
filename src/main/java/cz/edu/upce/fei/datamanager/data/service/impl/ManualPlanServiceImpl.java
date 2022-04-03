package cz.edu.upce.fei.datamanager.data.service.impl;

import cz.edu.upce.fei.datamanager.data.entity.plan.ManualPlan;
import cz.edu.upce.fei.datamanager.data.repository.ManualPlanRepository;
import cz.edu.upce.fei.datamanager.data.service.ManualPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManualPlanServiceImpl implements ManualPlanService {

    private final ManualPlanRepository manualPlanRepository;

    @Override
    public List<ManualPlan> findAllManualPlans() {
        return manualPlanRepository.findAll();
    }

    @Override
    public List<ManualPlan> findAllManualPlans(String nameFilter) {
        return manualPlanRepository.findByName(nameFilter);
    }

    @Override
    public void saveManualPlan(ManualPlan manualPlan) {
        manualPlanRepository.save(manualPlan);
    }

    @Override
    public void deleteManualPlan(ManualPlan manualPlan) {
        manualPlanRepository.delete(manualPlan);
    }
}
