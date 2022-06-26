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
    @Column(name = "pin_name")
    private RaspiPin pin;
    @Column(name = "pin_address")
    private int pinAddress;
    @Enumerated(EnumType.STRING)
    private PinState defaultState;
}
