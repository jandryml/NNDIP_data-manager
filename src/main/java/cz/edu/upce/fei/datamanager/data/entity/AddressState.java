package cz.edu.upce.fei.datamanager.data.entity;

import cz.edu.upce.fei.datamanager.data.entity.enums.OutputType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@IdClass(AddressStateId.class)
public class AddressState {
    @Id
    @Enumerated(EnumType.STRING)
    private OutputType outputType;
    @Id
    private String address;
    @Min(0)
    @NotBlank
    private String value;
}
