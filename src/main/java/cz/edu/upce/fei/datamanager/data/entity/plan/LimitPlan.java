package cz.edu.upce.fei.datamanager.data.entity.plan;

import cz.edu.upce.fei.datamanager.data.entity.enums.LimitPlanType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class LimitPlan extends Plan {
    @Enumerated(EnumType.STRING)
    private LimitPlanType valueType;
    private Double optimalValue;
    private Double thresholdValue;
}
