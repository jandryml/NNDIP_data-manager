package cz.edu.upce.fei.datamanager.data.service.impl;

import com.vaadin.flow.router.NotFoundException;
import cz.edu.upce.fei.datamanager.data.dto.DashboardSensorDataDto;
import cz.edu.upce.fei.datamanager.data.entity.DashboardSensorConfig;
import cz.edu.upce.fei.datamanager.data.entity.Sensor;
import cz.edu.upce.fei.datamanager.data.entity.SensorData;
import cz.edu.upce.fei.datamanager.data.entity.enums.MeasuredValueType;
import cz.edu.upce.fei.datamanager.data.repository.DashboardSensorConfigRepository;
import cz.edu.upce.fei.datamanager.data.service.DashboardService;
import cz.edu.upce.fei.datamanager.data.service.SensorDataService;
import liquibase.pro.packaged.P;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final DashboardSensorConfigRepository dashboardSensorConfigRepository;
    private final SensorDataService sensorDataService;

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

    @Override
    public List<Sensor> getSensorsViewableInDashboard(MeasuredValueType valueType) {
        return dashboardSensorConfigRepository.findByMeasuredValueType(valueType).stream()
                .map(DashboardSensorConfig::getSensor)
                .toList();
    }

    @Override
    public List<SensorData> getSensorData(Sensor sensor, MeasuredValueType valueType, LocalDate date) {
        return sensorDataService.getDataBy(sensor, date.atStartOfDay(), date.atStartOfDay().plusDays(1).minusNanos(1));
    }

    @Override
    public void setSensorsViewableInDashboard(MeasuredValueType valueType, List<Sensor> sensors) {
        dashboardSensorConfigRepository.deleteAllByMeasuredValueType(valueType);

        List<DashboardSensorConfig> dashboardSensorConfigs = sensors.stream()
                .map(it -> new DashboardSensorConfig(it, valueType))
                .toList();

        dashboardSensorConfigRepository.saveAll(dashboardSensorConfigs);
    }

    private List<DashboardSensorDataDto> getSensorDataForDashboard(MeasuredValueType valueType) {
        List<Sensor> sensors = getSensorsViewableInDashboard(valueType);

        List<DashboardSensorDataDto> dashboardSensorDataDtos = new ArrayList<>();

        sensors.forEach(it -> {
            //TODO refactor, get latest value, but if it is older than 15 minutes, make it unknown - to view disconnection
            String value;
            try {
                SensorData sensorData = sensorDataService.getLatestData(it.getId());

                value = switch (valueType) {
                    case TEMPERATURE -> sensorData.getTemperature().toString();
                    case HUMIDITY -> sensorData.getHumidity().toString();
                    case CO2 -> sensorData.getCo2().toString();
                };
            } catch (NotFoundException ex) {
                value = "Unknown";
            }
            dashboardSensorDataDtos.add(new DashboardSensorDataDto(it.getName(), value));
        });
        return dashboardSensorDataDtos;
    }
}
