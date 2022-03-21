package cz.edu.upce.fei.datamanager.data.service;

import cz.edu.upce.fei.datamanager.data.entity.Sensor;

import java.util.List;

public interface SensorService {

    List<Sensor> findAllSensors();

    List<Sensor> findAllSensors(String stringFilter);

    void saveSensor(Sensor sensor);

    void deleteSensor(Sensor sensor);
}
