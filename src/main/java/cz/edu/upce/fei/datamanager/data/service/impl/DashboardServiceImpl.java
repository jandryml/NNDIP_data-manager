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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static cz.edu.upce.fei.datamanager.data.entity.enums.MeasuredValueType.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final DashboardSensorConfigRepository dashboardSensorConfigRepository;
    private final SensorDataService sensorDataService;

    @Value("${dashboard.sensorData.maxAgeInMinutes:15}")
    private int maxMinutesAgeOfDataAllowed;

    @Override
    public List<DashboardSensorDataDto> getViewableTemperatureData() {
        return getSensorDataForDashboard(TEMPERATURE);
    }

    @Override
    public List<DashboardSensorDataDto> getViewableHumidityData() {
        return getSensorDataForDashboard(HUMIDITY);
    }

    @Override
    public List<DashboardSensorDataDto> getViewableCo2Data() {
        return getSensorDataForDashboard(CO2);
    }

    @Override
    public List<Sensor> getSensorsViewableInDashboard(MeasuredValueType valueType) {
        return dashboardSensorConfigRepository.findByMeasuredValueType(valueType).stream()
                .map(DashboardSensorConfig::getSensor)
                .toList();
    }

    @Override
    public List<SensorData> getSensorData(Sensor sensor, MeasuredValueType valueType, LocalDate date) {
        List<SensorData> allSensorData = sensorDataService.getDataBy(sensor, date.atStartOfDay(), date.atStartOfDay().plusDays(1).minusNanos(1));
        return allSensorData.stream().filter(sensorData -> filterNullValues(sensorData, valueType)).collect(Collectors.toList());
    }


    @Override
    public void setSensorsViewableInDashboard(MeasuredValueType valueType, List<Sensor> sensors) {
        dashboardSensorConfigRepository.deleteAllByMeasuredValueType(valueType);

        List<DashboardSensorConfig> dashboardSensorConfigs = sensors.stream()
                .map(it -> new DashboardSensorConfig(it, valueType))
                .toList();

        dashboardSensorConfigRepository.saveAll(dashboardSensorConfigs);
    }

    private boolean filterNullValues(SensorData sensorData, MeasuredValueType valueType) {
        return switch (valueType) {
            case TEMPERATURE -> sensorData.getTemperature() != null;
            case HUMIDITY -> sensorData.getHumidity() != null;
            case CO2 -> sensorData.getCo2() != null;
        };
    }

    private List<DashboardSensorDataDto> getSensorDataForDashboard(MeasuredValueType valueType) {
        List<Sensor> sensors = getSensorsViewableInDashboard(valueType);

        List<DashboardSensorDataDto> dashboardSensorDataDtos = new ArrayList<>();

        sensors.forEach(sensor -> {
            //TODO refactor, get latest value, but if it is older than 15 minutes, make it unknown - to view disconnection
            String value = evaluateSensorDataForDashboard(valueType, sensor);
            dashboardSensorDataDtos.add(new DashboardSensorDataDto(sensor.getName(), value));
        });
        return dashboardSensorDataDtos;
    }

    private String evaluateSensorDataForDashboard(MeasuredValueType valueType, Sensor sensor) {
        String value;
        try {
            SensorData sensorData = sensorDataService.getLatestData(sensor.getId());
            boolean oldData = isDataOld(sensorData);
            if (oldData) {
                log.warn("Sensor {} data found but is older than {} ", sensor, maxMinutesAgeOfDataAllowed);
            }
            value = switch (valueType) {
                case TEMPERATURE -> formatSensorData(sensorData.getTemperature(), TEMPERATURE.getUnits(), oldData);
                case HUMIDITY -> formatSensorData(sensorData.getHumidity(), HUMIDITY.getUnits(), oldData);
                case CO2 -> formatSensorData(sensorData.getCo2(), CO2.getUnits(), oldData);
            };
        } catch (NotFoundException ex) {
            value = "Not available";
            log.warn("Data for sensor {} not found!", sensor);
        }
        return value;
    }

    private String formatSensorData(Number data, String unit, boolean oldData) {
        if (data != null) {
            String result = data + " " + unit;

            if (oldData) {
                result += " -  ! old data !";
            }

            return result;
        } else {
            return "Not available";
        }
    }

    private boolean isDataOld(SensorData sensorData) {
        return sensorData.getTimestamp().toLocalDateTime()
                .isBefore(LocalDateTime.now().minusMinutes(maxMinutesAgeOfDataAllowed + 1));
    }
}
