package cz.edu.upce.fei.datamanager.data.entity;

import cz.edu.upce.fei.datamanager.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Entity
public class ThresholdAction extends AbstractEntity {
    private String name;
    private String address;
    private String value;
    @Enumerated(EnumType.STRING)
    private OutputType outputType;
}
