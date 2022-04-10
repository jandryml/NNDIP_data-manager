package cz.edu.upce.fei.datamanager.data.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MeasuredValueType {
    TEMPERATURE(SensorType.TEMPERATURE, "Temperature", "°C"),
    HUMIDITY(SensorType.HUMIDITY, "Humidity", "%"),
    CO2(SensorType.CO2, "Co2", "ppm");

    private final SensorType sensorType;
    private final String name;
    private final String units;
}

