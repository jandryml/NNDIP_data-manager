package cz.edu.upce.fei.datamanager.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Event extends AbstractEntity {
    @NotBlank
    private String name;
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "event_actions",
            inverseJoinColumns = @JoinColumn(
                    name = "action_id",
                    referencedColumnName = "id"
            ))
    private List<Action> actionList = new ArrayList<>();
}
