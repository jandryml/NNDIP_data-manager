package cz.edu.upce.fei.datamanager.data.service;


import cz.edu.upce.fei.datamanager.data.entity.SensorData;

import java.time.LocalDateTime;
import java.util.List;

public interface SensorDataService {

    SensorData getLatestData(long sensorId);

    List<SensorData> getDataBetween(LocalDateTime from, LocalDateTime to);
}
