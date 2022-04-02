package cz.edu.upce.fei.datamanager.data.service;

import cz.edu.upce.fei.datamanager.data.dto.DashboardSensorDataDto;
import cz.edu.upce.fei.datamanager.data.entity.Sensor;
import cz.edu.upce.fei.datamanager.data.entity.enums.MeasuredValueType;

import java.util.List;

public interface DashboardService {

    List<DashboardSensorDataDto> getViewableTemperatureData();

    List<DashboardSensorDataDto> getViewableHumidityData();

    List<DashboardSensorDataDto> getViewableCo2Data();

    List<Sensor> getSensorsViewableInDashboard(MeasuredValueType valueType);

    void setSensorsViewableInDashboard(MeasuredValueType valueType, List<Sensor> sensors);
}
