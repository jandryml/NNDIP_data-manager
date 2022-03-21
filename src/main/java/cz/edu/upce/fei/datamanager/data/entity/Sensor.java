package cz.edu.upce.fei.datamanager.data.entity;

import cz.edu.upce.fei.datamanager.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Entity
public class Sensor extends AbstractEntity {
    @NotEmpty
    private String name;
    // TODO for possible extension with multiple devices (raspberries)
    private Long deviceId = 1L;
    // Used only for listing
    @Enumerated(EnumType.STRING)
    private SensorType sensorType;
}
