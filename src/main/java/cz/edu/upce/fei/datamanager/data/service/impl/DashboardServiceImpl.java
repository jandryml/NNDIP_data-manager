package cz.edu.upce.fei.datamanager.data.service.impl;

import cz.edu.upce.fei.datamanager.data.dto.DashboardSensorDataDto;
import cz.edu.upce.fei.datamanager.data.service.DashboardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Override
    public List<DashboardSensorDataDto> getViewableTemperatureData() {
        return List.of(
                new DashboardSensorDataDto("Obyvak", Double.valueOf(25.1).toString())
        );
    }

    @Override
    public List<DashboardSensorDataDto> getViewableHumidityData() {
        return List.of(
                new DashboardSensorDataDto("Obyvak", Double.valueOf(35.1).toString())
        );
    }

    @Override
    public List<DashboardSensorDataDto> getViewableCo2Data() {
        return List.of(
                new DashboardSensorDataDto("Obyvak", Double.valueOf(550).toString()),
                new DashboardSensorDataDto("Pokoj", Double.valueOf(320).toString()),
                new DashboardSensorDataDto("Loznice", Double.valueOf(420).toString())
        );
    }
}
