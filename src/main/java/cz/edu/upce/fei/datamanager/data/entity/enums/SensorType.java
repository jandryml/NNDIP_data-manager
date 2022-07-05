package cz.edu.upce.fei.datamanager.data.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SensorType {
    TEMPERATURE("Temperature"),
    HUMIDITY("Humidity"),
    CO2("CO2"),
    MIXED("Mixed");

    private final String prettyName;
}
