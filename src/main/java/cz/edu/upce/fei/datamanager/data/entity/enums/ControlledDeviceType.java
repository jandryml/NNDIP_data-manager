package cz.edu.upce.fei.datamanager.data.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ControlledDeviceType {
    RECUPERATION("Recuperation"),
    AC_STATUS("Ac status"),
    AC_FAN("Ac fan"),
    AC_MODE("Ac mode");

    private final String prettyName;
}
