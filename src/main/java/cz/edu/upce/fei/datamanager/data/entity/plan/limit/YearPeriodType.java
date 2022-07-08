package cz.edu.upce.fei.datamanager.data.entity.plan.limit;

import lombok.Getter;

@Getter
public enum YearPeriodType {
    WINTER("Winter plan"),
    SUMMER("Summer plan");
    
    private String prettyName;

    YearPeriodType(String prettyName) {
        this.prettyName = prettyName;
    }
}
