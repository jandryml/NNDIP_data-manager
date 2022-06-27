package cz.edu.upce.fei.datamanager.data.entity.plan.limit;

import cz.edu.upce.fei.datamanager.data.entity.enums.LimitPlanType;
import cz.edu.upce.fei.datamanager.data.entity.plan.Plan;
import cz.edu.upce.fei.datamanager.data.entity.plan.PlanType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
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
    @OneToOne
    private YearPeriod yearPeriod;

    public LimitPlan() {
        this.planType = PlanType.LIMIT_PLAN;
        this.priority = PlanType.LIMIT_PLAN.getDefaultPriority();
    }

    public LimitPlan(LimitPlanType valueType, Double optimalValue, Double thresholdValue) {
        this.valueType = valueType;
        this.optimalValue = optimalValue;
        this.thresholdValue = thresholdValue;
        this.planType = PlanType.LIMIT_PLAN;
        this.priority = PlanType.LIMIT_PLAN.getDefaultPriority();
    }
}
