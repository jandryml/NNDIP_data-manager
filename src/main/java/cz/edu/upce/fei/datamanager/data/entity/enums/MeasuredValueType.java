package cz.edu.upce.fei.datamanager.data.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MeasuredValueType {
    TEMPERATURE(SensorType.TEMPERATURE),
    HUMIDITY(SensorType.HUMIDITY),
    CO2(SensorType.CO2);

    private final SensorType sensorType;
}

