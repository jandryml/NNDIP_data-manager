package cz.edu.upce.fei.datamanager.data.entity.plan;

import cz.edu.upce.fei.datamanager.data.entity.AbstractEntity;
import cz.edu.upce.fei.datamanager.data.entity.Event;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Plan extends AbstractEntity {
    @NotBlank
    protected String name;
    protected boolean enabled;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    protected Event event;
}
