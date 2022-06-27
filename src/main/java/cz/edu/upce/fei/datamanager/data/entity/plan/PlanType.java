package cz.edu.upce.fei.datamanager.data.entity.plan;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PlanType {
    MANUAL_GPIO_PLAN(100),
    MANUAL_PLAN(100),
    TIME_GPIO_PLAN(90),
    TIME_PLAN(50),
    LIMIT_PLAN(0);

    private final int defaultPriority;
}
