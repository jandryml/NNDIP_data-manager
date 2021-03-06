package cz.edu.upce.fei.datamanager.data.service.impl;

import cz.edu.upce.fei.datamanager.data.entity.Sensor;
import cz.edu.upce.fei.datamanager.data.entity.enums.SensorType;
import cz.edu.upce.fei.datamanager.data.repository.SensorRepository;
import cz.edu.upce.fei.datamanager.data.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SensorServiceImpl implements SensorService {

    private final SensorRepository sensorRepository;

    @Override
    public List<Sensor> findAllSensors() {
        return sensorRepository.findAll();
    }

    @Override
    public List<Sensor> findByName(String stringFilter) {
        return sensorRepository.findByName(stringFilter);
    }

    public List<Sensor> findBySensorTypes(List<SensorType> sensorTypes) {
        return sensorRepository.findBySensorTypeIn(sensorTypes);
    }

    @Override
    public void saveSensor(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    @Override
    public void deleteSensor(Sensor sensor) {
        sensorRepository.delete(sensor);
    }
}
