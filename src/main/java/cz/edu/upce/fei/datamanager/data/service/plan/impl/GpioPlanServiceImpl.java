package cz.edu.upce.fei.datamanager.data.service.plan.impl;

import cz.edu.upce.fei.datamanager.data.entity.plan.gpio.GpioPlan;
import cz.edu.upce.fei.datamanager.data.entity.plan.gpio.ManualGpioPlan;
import cz.edu.upce.fei.datamanager.data.entity.plan.gpio.TimeGpioPlan;
import cz.edu.upce.fei.datamanager.data.repository.plan.ManualGpioPlanRepository;
import cz.edu.upce.fei.datamanager.data.repository.plan.TimeGpioPlanRepository;
import cz.edu.upce.fei.datamanager.data.service.plan.GpioPlanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class GpioPlanServiceImpl implements GpioPlanService {

    private final ManualGpioPlanRepository manualGpioPlanRepository;
    private final TimeGpioPlanRepository timeGpioPlanRepository;

    @Override
    public List<GpioPlan> findAllGpioPlans() {
        List<GpioPlan> planList = new ArrayList<>();

        planList.addAll(manualGpioPlanRepository.findAll());
        planList.addAll(timeGpioPlanRepository.findAll());

        return planList;
    }

    @Override
    public List<GpioPlan> findAllGpioPlans(String nameFilter) {
        List<GpioPlan> planList = new ArrayList<>();

        planList.addAll(manualGpioPlanRepository.findByName(nameFilter));
        planList.addAll(timeGpioPlanRepository.findByName(nameFilter));

        return planList;
    }

    @Override
    public void saveGpioPlan(GpioPlan gpioPlan) {
        if (gpioPlan instanceof TimeGpioPlan) {
            timeGpioPlanRepository.save((TimeGpioPlan) gpioPlan);
        } else if (gpioPlan instanceof ManualGpioPlan) {
            manualGpioPlanRepository.save((ManualGpioPlan) gpioPlan);
        } else {
            log.error("Unknown Gpio plan type");
        }
    }

    @Override
    public void deleteGpioPlan(GpioPlan gpioPlan) {
        if (gpioPlan instanceof TimeGpioPlan) {
            timeGpioPlanRepository.delete((TimeGpioPlan) gpioPlan);
        } else if (gpioPlan instanceof ManualGpioPlan) {
            manualGpioPlanRepository.delete((ManualGpioPlan) gpioPlan);
        } else {
            log.error("Unknown Gpio plan type");
        }
    }
}
