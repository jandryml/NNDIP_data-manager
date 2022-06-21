package cz.edu.upce.fei.datamanager.data.service.plan.impl;

import cz.edu.upce.fei.datamanager.data.entity.plan.TimePlan;
import cz.edu.upce.fei.datamanager.data.repository.plan.TimePlanRepository;
import cz.edu.upce.fei.datamanager.data.service.plan.TimePlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimePlanServiceImpl implements TimePlanService {

    private final TimePlanRepository timePlanRepository;
    @Override
    public List<TimePlan> findAllTimePlans() {
        return timePlanRepository.findAll();
    }

    @Override
    public List<TimePlan> findAllTimePlans(String nameFilter) {
        return timePlanRepository.findByName(nameFilter);
    }

    @Override
    public void saveTimePlan(TimePlan timePlan) {
        timePlanRepository.save(timePlan);
    }

    @Override
    public void deleteTimePlan(TimePlan timePlan) {
        timePlanRepository.delete(timePlan);
    }
}
