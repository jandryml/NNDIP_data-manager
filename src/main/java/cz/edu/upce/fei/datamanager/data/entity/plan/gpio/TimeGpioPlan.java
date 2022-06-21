package cz.edu.upce.fei.datamanager.data.entity.plan.gpio;

import cz.edu.upce.fei.datamanager.data.entity.plan.PlanType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "time_gpio_plan")
public class TimeGpioPlan extends GpioPlan {
    @Min(1)
    private int duration;
    private LocalDateTime lastTriggered;

    public TimeGpioPlan() {
        this.planType = PlanType.TIME_GPIO_PLAN;
    }
}
