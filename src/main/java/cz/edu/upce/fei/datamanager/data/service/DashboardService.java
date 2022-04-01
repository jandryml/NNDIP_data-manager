package cz.edu.upce.fei.datamanager.data.service;

import cz.edu.upce.fei.datamanager.data.dto.DashboardSensorDataDto;

import java.util.List;

public interface DashboardService {

    List<DashboardSensorDataDto> getViewableTemperatureData();
    List<DashboardSensorDataDto> getViewableHumidityData();
    List<DashboardSensorDataDto> getViewableCo2Data();
}
