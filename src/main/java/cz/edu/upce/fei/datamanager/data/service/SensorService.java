package cz.edu.upce.fei.datamanager.data.service;

import cz.edu.upce.fei.datamanager.data.entity.Sensor;
import cz.edu.upce.fei.datamanager.data.entity.enums.SensorType;

import java.util.List;

public interface SensorService {

    List<Sensor> findAllSensors();

    List<Sensor> findByName(String stringFilter);

    List<Sensor> findBySensorTypes(List<SensorType> sensorTypes);

    void saveSensor(Sensor sensor);

    void deleteSensor(Sensor sensor);
}
