package cz.edu.upce.fei.datamanager.data.entity.plan.limit;

import lombok.Getter;

@Getter
public enum YearPeriodType {
    WINTER("Winter"),
    SUMMER("Summer");
    
    private String name;

    YearPeriodType(String name) {
        this.name = name;
    }
}
