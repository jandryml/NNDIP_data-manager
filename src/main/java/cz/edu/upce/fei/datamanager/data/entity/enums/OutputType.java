package cz.edu.upce.fei.datamanager.data.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OutputType {
    MODBUS_VALUE("MODBUS - register"),
    MODBUS_BOOLEAN("MODBUS - coil"),
    RASPBERRY_PIN("RaspberryPi GPIO");

    private final String prettyName;
}
