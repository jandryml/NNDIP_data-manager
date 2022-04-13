package cz.edu.upce.fei.datamanager.data.entity.plan;

import cz.edu.upce.fei.datamanager.data.entity.enums.LimitPlanType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class LimitPlan extends Plan {
    @NotNull
    @Enumerated(EnumType.STRING)
    private LimitPlanType valueType;
    @NotNull
    private Double optimalValue;
    @NotNull
    private Double thresholdValue;
}
