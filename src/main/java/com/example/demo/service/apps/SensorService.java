package com.example.demo.service.apps;

import com.example.demo.model.Sensor;

import java.util.List;

public interface SensorService {
    List<Sensor> getAllSensors();
    Sensor getSensorById(Integer id);
    Sensor saveSensor(Sensor sensor);
    void deleteSensor(Integer id);
    List<Sensor> searchSensorsByName(String name);
    List<Sensor> searchSensorsByModel(String model);


    Sensor createSensor(Sensor sensor);
    Sensor updateSensor(Integer id, Sensor sensorDetails);
}
