package cz.edu.upce.fei.datamanager.data.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ControlledDeviceType {
    RECUPERATION("Recuperation"),
    AC("Air conditioning"),
    FAN("Fan");

    private final String name;
}
