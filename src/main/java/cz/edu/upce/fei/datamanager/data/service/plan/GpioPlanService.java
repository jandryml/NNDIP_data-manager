package cz.edu.upce.fei.datamanager.data.service.plan;

import cz.edu.upce.fei.datamanager.data.entity.plan.gpio.GpioPlan;

import java.util.List;

public interface GpioPlanService {
    List<GpioPlan> findAllGpioPlans();

    List<GpioPlan> findAllGpioPlans(String name);

    void saveGpioPlan(GpioPlan gpioPlan);

    void deleteGpioPlan(GpioPlan gpioPlan);
}
