package cz.edu.upce.fei.datamanager.data.entity.plan.gpio;

import cz.edu.upce.fei.datamanager.data.entity.plan.PlanType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity(name = "manual_gpio_plan")
public class ManualGpioPlan extends GpioPlan{
    private boolean active;

    public ManualGpioPlan() {
        this.planType = PlanType.MANUAL_GPIO_PLAN;
    }
}
