package cz.edu.upce.fei.datamanager.data.entity.plan;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class ManualPlan extends Plan{
    public ManualPlan() {
        this.planType = PlanType.MANUAL_PLAN;
    }
}
