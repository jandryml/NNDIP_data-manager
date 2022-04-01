package cz.edu.upce.fei.datamanager.data.service;

import cz.edu.upce.fei.datamanager.data.entity.plan.TimePlan;

import java.util.List;

public interface TimePlanService {
    List<TimePlan> findAllTimePlans();

    List<TimePlan> findAllTimePlans(String nameFilter);

    void saveTimePlan(TimePlan timePlan);

    void deleteTimePlan(TimePlan timePlan);
}
