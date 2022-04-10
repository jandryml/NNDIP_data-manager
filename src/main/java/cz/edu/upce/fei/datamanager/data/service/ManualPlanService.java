package cz.edu.upce.fei.datamanager.data.service;

import cz.edu.upce.fei.datamanager.data.entity.plan.ManualPlan;

import java.util.List;

public interface ManualPlanService {
    List<ManualPlan> findAllManualPlans();

    List<ManualPlan> findAllManualPlans(String nameFilter);

    void saveManualPlan(ManualPlan manualPlan);

    void deleteManualPlan(ManualPlan manualPlan);
}
