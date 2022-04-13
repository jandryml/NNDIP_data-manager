package cz.edu.upce.fei.datamanager.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "data")
public class SensorData extends AbstractEntity {
    @NotNull
    private Long sensorId;
    @NotNull
    @Column(name = "data_timestamp")
    private Timestamp timestamp;
    @Min(0)
    @NotNull
    private Integer hits;
    private Double temperature;
    private Double humidity;
    private Integer co2;
}