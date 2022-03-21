package cz.edu.upce.fei.datamanager.data.entity;

import cz.edu.upce.fei.datamanager.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Plan extends AbstractEntity {
    protected String name;
    protected boolean enabled;
    @ManyToMany(fetch = FetchType.EAGER)
    protected List<Action> actionList;
}
