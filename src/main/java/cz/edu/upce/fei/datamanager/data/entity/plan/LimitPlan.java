package cz.edu.upce.fei.datamanager.data.entity.plan;

import cz.edu.upce.fei.datamanager.data.entity.Sensor;
import cz.edu.upce.fei.datamanager.data.entity.enums.MeasuredValueType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class LimitPlan extends Plan {
    @Enumerated(EnumType.STRING)
    private MeasuredValueType valueType;
    private String threshold;
    private String tolerance;
    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;
}
