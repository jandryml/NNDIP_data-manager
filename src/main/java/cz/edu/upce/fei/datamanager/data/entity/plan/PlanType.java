package cz.edu.upce.fei.datamanager.data.entity.plan;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PlanType {
    MANUAL_GPIO_PLAN("Manual GPIO plan", 100),
    MANUAL_PLAN("Manual plan", 100),
    TIME_GPIO_PLAN("Time GPIO plan", 90),
    TIME_PLAN("Time plan", 50),
    LIMIT_PLAN("Limit plan", 0);

    private final String prettyName;
    private final int defaultPriority;
}
