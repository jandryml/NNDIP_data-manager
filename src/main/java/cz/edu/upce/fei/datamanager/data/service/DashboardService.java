package cz.edu.upce.fei.datamanager.data.service;

import cz.edu.upce.fei.datamanager.data.dto.DashboardSensorDataDto;
import cz.edu.upce.fei.datamanager.data.entity.Sensor;
import cz.edu.upce.fei.datamanager.data.entity.SensorData;
import cz.edu.upce.fei.datamanager.data.entity.enums.MeasuredValueType;

import java.time.LocalDate;
import java.util.List;

public interface DashboardService {

    List<DashboardSensorDataDto> getViewableTemperatureData();

    List<DashboardSensorDataDto> getViewableHumidityData();

    List<DashboardSensorDataDto> getViewableCo2Data();

    List<Sensor> getSensorsViewableInDashboard(MeasuredValueType valueType);

    List<SensorData> getSensorData(Sensor sensor, MeasuredValueType valueType, LocalDate date);

    void setSensorsViewableInDashboard(MeasuredValueType valueType, List<Sensor> sensors);
}
