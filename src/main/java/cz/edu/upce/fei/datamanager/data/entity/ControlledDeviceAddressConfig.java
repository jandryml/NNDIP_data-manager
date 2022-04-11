package cz.edu.upce.fei.datamanager.data.entity;

import cz.edu.upce.fei.datamanager.data.entity.enums.ControlledDeviceType;
import cz.edu.upce.fei.datamanager.data.entity.enums.OutputType;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ControlledDeviceAddressConfig {
    @Id
    @Enumerated(EnumType.STRING)
    private ControlledDeviceType controlledDeviceType;
    @NotNull
    @Enumerated(EnumType.STRING)
    private OutputType outputType;
    @Min(0)
    @NotBlank
    private String address;
}
