package cz.edu.upce.fei.datamanager.data.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "data")
@IdClass(SensorDataId.class)
public class SensorData {
    @Id
    private long sensorId;
    @Id
    @Column(name = "data_time")
    private Timestamp timestamp;
    private int hits;
    @Column(name = "temperature_1")
    private Double temperature1;
    private Double humidity;
    private Integer co2_1;
    private Integer co2_2;
    @Column(name = "temperature_2")
    private Integer temperature2;
}