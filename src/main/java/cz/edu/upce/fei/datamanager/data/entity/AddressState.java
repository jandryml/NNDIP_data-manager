package cz.edu.upce.fei.datamanager.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"outputType", "address"}))
public class AddressState extends AbstractEntity {
    @Enumerated(EnumType.STRING)
    @NotBlank
    private OutputType outputType;
    @NotBlank
    @Min(0)
    private String address;
    @NotBlank
    @Min(0)
    private String value;
    @Enumerated(EnumType.STRING)
    private PriorityType priority;
}
