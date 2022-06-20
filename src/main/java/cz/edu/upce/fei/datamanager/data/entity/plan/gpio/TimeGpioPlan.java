package cz.edu.upce.fei.datamanager.data.entity.plan.gpio;

import cz.edu.upce.fei.datamanager.data.entity.plan.PlanType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Getter
@Setter
@Entity(name = "time_gpio_plan")
public class TimeGpioPlan extends GpioPlan {
    @Min(1)
    private int duration;
    @NotNull
    private LocalTime lastLaunched;

    public TimeGpioPlan() {
        this.planType = PlanType.TIME_GPIO_PLAN;
    }
}
