package cz.edu.upce.fei.datamanager.data.entity;

import cz.edu.upce.fei.datamanager.data.entity.enums.SensorType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@ToString
public class Sensor extends AbstractEntity {
    @NotBlank
    private String name;
    // Used only for listing
    @NotNull
    @Enumerated(EnumType.STRING)
    private SensorType sensorType;
}
