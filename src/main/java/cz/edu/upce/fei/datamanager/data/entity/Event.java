package cz.edu.upce.fei.datamanager.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Event extends AbstractEntity {
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Action> actionList = new ArrayList<>();
}
