package cz.edu.upce.fei.datamanager.data.entity;

import cz.edu.upce.fei.datamanager.data.entity.enums.MeasuredValueType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@IdClass(DashboardSensorConfig.class)
public class DashboardSensorConfig implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;
    @Id
    @Enumerated(EnumType.STRING)
    private MeasuredValueType measuredValueType;
}
