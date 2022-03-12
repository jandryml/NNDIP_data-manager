package cz.edu.upce.fei.datamanager.data.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
public class SensorDataId implements Serializable {
    private long sensorId;
    private Timestamp timestamp;
}
