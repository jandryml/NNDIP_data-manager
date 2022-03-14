package cz.edu.upce.fei.datamanager.data.entity;

import cz.edu.upce.fei.datamanager.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Sensor extends AbstractEntity {
    private String name;
    // TODO for possible extension with multiple devices (raspberries)
    private Long deviceId = 1L;
}
