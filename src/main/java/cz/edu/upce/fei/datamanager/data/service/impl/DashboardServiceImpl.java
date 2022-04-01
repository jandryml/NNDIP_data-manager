package cz.edu.upce.fei.datamanager.data.service.impl;

import cz.edu.upce.fei.datamanager.data.dto.DashboardSensorDataDto;
import cz.edu.upce.fei.datamanager.data.entity.DashboardSensorConfig;
import cz.edu.upce.fei.datamanager.data.entity.Sensor;
import cz.edu.upce.fei.datamanager.data.entity.enums.MeasuredValueType;
import cz.edu.upce.fei.datamanager.data.entity.enums.SensorType;
import cz.edu.upce.fei.datamanager.data.repository.DashboardSensorConfigRepository;
import cz.edu.upce.fei.datamanager.data.repository.SensorDataRepository;
import cz.edu.upce.fei.datamanager.data.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final DashboardSensorConfigRepository dashboardSensorConfigRepository;
    private final SensorDataRepository sensorDataRepository;

    @Override
    public List<DashboardSensorDataDto> getViewableTemperatureData() {
        return getSensorDataForDashboard(MeasuredValueType.TEMPERATURE);
    }

    @Override
    public List<DashboardSensorDataDto> getViewableHumidityData() {
        return getSensorDataForDashboard(MeasuredValueType.HUMIDITY);
    }

    @Override
    public List<DashboardSensorDataDto> getViewableCo2Data() {
        return getSensorDataForDashboard(MeasuredValueType.CO2);
    }

    private List<DashboardSensorDataDto> getSensorDataForDashboard(MeasuredValueType valueType) {
        List<Sensor> sensors = dashboardSensorConfigRepository.findByMeasuredValueType(valueType).stream()
                .map(DashboardSensorConfig::getSensor)
                .collect(Collectors.toList());

        List<DashboardSensorDataDto> dashboardSensorDataDtos = new ArrayList<>();

        sensors.forEach(it -> {
            //TODO refactor, get latest value, but if it is older than 15 minutes, make it unknown - to view disconnection
            String value = sensorDataRepository.findLastValueBySensorId(it.getId())
                    .map(sensorData -> switch (valueType) {
                        case TEMPERATURE -> sensorData.getTemperature().toString();
                        case HUMIDITY -> sensorData.getHumidity().toString();
                        case CO2 -> sensorData.getCo2().toString();
                    })
                    .orElse("Unknown");

            dashboardSensorDataDtos.add(new DashboardSensorDataDto(it.getName(), value));
        });
        return dashboardSensorDataDtos;
    }
}
