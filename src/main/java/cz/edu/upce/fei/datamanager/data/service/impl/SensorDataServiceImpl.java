package cz.edu.upce.fei.datamanager.data.service.impl;

import com.vaadin.flow.router.NotFoundException;
import cz.edu.upce.fei.datamanager.data.entity.SensorData;
import cz.edu.upce.fei.datamanager.data.repository.SensorDataRepository;
import cz.edu.upce.fei.datamanager.data.service.SensorDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SensorDataServiceImpl implements SensorDataService {

    private final SensorDataRepository sensorDataRepository;

    @Override
    public SensorData getLatestData(long sensorId) {
        return sensorDataRepository.findLastValueBySensorId(sensorId)
                .orElseThrow(() -> new NotFoundException("Sensor with id '" + sensorId + "' has no data"));
    }

    @Override
    public List<SensorData> getDataBetween(LocalDateTime from, LocalDateTime to) {
        return sensorDataRepository.findValuesBetween(Timestamp.valueOf(from), Timestamp.valueOf(to));
    }
}
