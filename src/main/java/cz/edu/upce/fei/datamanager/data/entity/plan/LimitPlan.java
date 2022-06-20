package cz.edu.upce.fei.datamanager.data.entity.plan;

import cz.edu.upce.fei.datamanager.data.entity.enums.LimitPlanType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
public class LimitPlan extends Plan {
    @NotNull
    @Enumerated(EnumType.STRING)
    private LimitPlanType valueType;
    @NotNull
    private Double optimalValue;
    @NotNull
    private Double thresholdValue;

    public LimitPlan() {
        this.planType = PlanType.TIME_PLAN;
    }

    public LimitPlan(LimitPlanType valueType, Double optimalValue, Double thresholdValue) {
        this.valueType = valueType;
        this.optimalValue = optimalValue;
        this.thresholdValue = thresholdValue;
        this.planType = PlanType.TIME_PLAN;
    }
}
