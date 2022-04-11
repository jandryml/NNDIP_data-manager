package cz.edu.upce.fei.datamanager.data.entity;

import cz.edu.upce.fei.datamanager.data.entity.enums.OutputType;
import cz.edu.upce.fei.datamanager.data.entity.enums.PriorityType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"outputType", "address", "priority"}))
public class AddressState extends AbstractEntity {
    @NotNull
    @Enumerated(EnumType.STRING)
    private OutputType outputType;
    @Min(0)
    @NotBlank
    private String address;
    @Min(0)
    @NotBlank
    private String value;
    @NotNull
    @Enumerated(EnumType.STRING)
    private PriorityType priority;
}
