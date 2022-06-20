package cz.edu.upce.fei.datamanager.data.entity.plan.gpio;

import cz.edu.upce.fei.datamanager.data.entity.plan.Plan;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "gpio_plan")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class GpioPlan extends Plan {
    @Enumerated(EnumType.STRING)
    private RaspiPin address;
    @Enumerated(EnumType.STRING)
    private PinState defaultState;
}
