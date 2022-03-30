package cz.edu.upce.fei.datamanager.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "data")
public class SensorData extends AbstractEntity {
    private long sensorId;
    @Column(name = "data_timestamp")
    private Timestamp timestamp;
    private int hits;
    private Double temperature;
    private Double humidity;
    private Integer co2;
}