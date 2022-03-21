package cz.edu.upce.fei.datamanager.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class LimitPlan extends Plan {
    @Enumerated(EnumType.STRING)
    private SensorType sensorType;
    private String threshold;
    private String tolerance;
    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;
}
