package cz.edu.upce.fei.datamanager.data.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ControlledDeviceType {
    RECUPERATION,
    AC_STATUS,
    AC_FAN,
    AC_MODE
}
