package cz.edu.upce.fei.datamanager.data.entity;

import cz.edu.upce.fei.datamanager.data.entity.enums.OutputType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
public class Action extends AbstractEntity {
    @NotBlank
    private String name;
    @NotBlank
    @Min(0)
    private String address;
    @NotBlank
    @Min(0)
    private String value;
    @NotNull
    @Enumerated(EnumType.STRING)
    private OutputType outputType;
}
