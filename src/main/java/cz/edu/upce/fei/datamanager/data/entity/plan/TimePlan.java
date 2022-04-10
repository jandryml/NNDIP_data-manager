package cz.edu.upce.fei.datamanager.data.entity.plan;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalTime;

@Getter
@Setter
@Entity
public class TimePlan extends Plan {
    private LocalTime fromTime;
    private LocalTime toTime;
}
