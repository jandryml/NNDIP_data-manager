package cz.edu.upce.fei.datamanager.data.entity.plan.limit;

import lombok.Getter;

@Getter
public enum YearPeriodType {
    WINTER("Winter"),
    SUMMER("Summer");
    
    private String prettyName;

    YearPeriodType(String prettyName) {
        this.prettyName = prettyName;
    }
}
