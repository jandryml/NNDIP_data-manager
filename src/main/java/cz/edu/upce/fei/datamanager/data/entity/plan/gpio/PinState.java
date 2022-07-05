package cz.edu.upce.fei.datamanager.data.entity.plan.gpio;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PinState {
    HIGH("High"),
    LOW("Low");

    private final String prettyName;
}
