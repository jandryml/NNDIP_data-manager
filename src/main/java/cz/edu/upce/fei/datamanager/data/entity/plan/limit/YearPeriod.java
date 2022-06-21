package cz.edu.upce.fei.datamanager.data.entity.plan.limit;

import cz.edu.upce.fei.datamanager.data.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Setter
@Getter
@Entity
public class YearPeriod extends AbstractEntity {
    @Enumerated(EnumType.STRING)
    private YearPeriodType name;
    private boolean active;
}
