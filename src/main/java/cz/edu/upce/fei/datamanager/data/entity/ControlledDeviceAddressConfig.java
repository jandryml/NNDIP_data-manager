package cz.edu.upce.fei.datamanager.data.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class ControlledDeviceAddressConfig {
    @Id
    @Enumerated(EnumType.STRING)
    private ControlledDeviceType controlledDeviceType;
    @Enumerated(EnumType.STRING)
    private OutputType outputType;
    @NotBlank
    @Min(0)
    private String address;
}
