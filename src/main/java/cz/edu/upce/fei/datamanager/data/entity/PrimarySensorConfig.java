package cz.edu.upce.fei.datamanager.data.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class PrimarySensorConfig {
    @Id
    @Enumerated(EnumType.STRING)
    private SensorType sensorType;
    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;
}
