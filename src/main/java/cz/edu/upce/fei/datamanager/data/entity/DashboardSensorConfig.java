package cz.edu.upce.fei.datamanager.data.entity;

import cz.edu.upce.fei.datamanager.data.entity.enums.MeasuredValueType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DashboardSensorConfig extends AbstractEntity implements Serializable {
    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;
    @Enumerated(EnumType.STRING)
    private MeasuredValueType measuredValueType;
}
