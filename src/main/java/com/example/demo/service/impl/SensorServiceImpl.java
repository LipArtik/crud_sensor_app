package com.example.demo.service.impl;

import com.example.demo.model.Range;
import com.example.demo.model.Sensor;
import com.example.demo.model.Type;
import com.example.demo.model.Unit;
import com.example.demo.repository.RangeRepository;
import com.example.demo.repository.SensorRepository;
import com.example.demo.repository.TypeRepository;
import com.example.demo.repository.UnitRepository;
import com.example.demo.service.apps.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorServiceImpl implements SensorService {

    @Autowired
    private SensorRepository sensorRepository;
    @Autowired
    private RangeRepository rangeRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private UnitRepository unitRepository;

    @Override
    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

    @Override
    public Sensor getSensorById(Integer id) {
        return sensorRepository.findById(id).orElse(null);
    }

    @Override
    public Sensor saveSensor(Sensor sensor) {
        Range range = sensor.getRange();
        Type type = sensor.getType();
        Unit unit = sensor.getUnit();

        rangeRepository.save(range);
        typeRepository.save(type);
        unitRepository.save(unit);

        return sensorRepository.save(sensor);
    }

    @Override
    public void deleteSensor(Integer id) {
        sensorRepository.deleteById(id);
    }

    @Override
    public List<Sensor> searchSensorsByName(String name) {
        return sensorRepository.findByNameContainingIgnoreCase(name);
    }

    public Sensor updateSensor(Integer id, Sensor sensorDetails) {
        Sensor existingSensor = getSensorById(id);
        if (existingSensor != null) {
            existingSensor.setName(sensorDetails.getName());
            existingSensor.setModel(sensorDetails.getModel());
            existingSensor.setLocation(sensorDetails.getLocation());


            return sensorRepository.save(existingSensor);
        }
        return null;
    }

    public Sensor createSensor(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    @Override
    public List<Sensor> searchSensorsByModel(String model) {
        return sensorRepository.findByModelContainingIgnoreCase(model);
    }
}
