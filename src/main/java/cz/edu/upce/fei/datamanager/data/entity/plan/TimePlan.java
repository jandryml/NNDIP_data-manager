package cz.edu.upce.fei.datamanager.data.entity.plan;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Getter
@Setter
@Entity
public class TimePlan extends Plan {
    @NotNull
    private LocalTime fromTime;
    @NotNull
    private LocalTime toTime;

    public TimePlan() {
        this.planType = PlanType.TIME_PLAN;
    }
}
