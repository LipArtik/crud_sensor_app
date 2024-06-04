package com.example.demo.controller;

import com.example.demo.model.Range;
import com.example.demo.model.Sensor;
import com.example.demo.model.Type;
import com.example.demo.model.Unit;
import com.example.demo.service.apps.SensorService;
import com.example.demo.service.apps.TypeService;
import com.example.demo.service.apps.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sensors")
@Validated
@SpringBootApplication
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @Autowired
    private TypeService typeService;
    @Autowired
    private UnitService unitService;

    @GetMapping
    public List<Sensor> getAllSensors() {
        return sensorService.getAllSensors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sensor> getSensorById(@PathVariable Integer id) {
        Sensor sensor = sensorService.getSensorById(id);
        return sensor != null ? ResponseEntity.ok(sensor) : ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSensor(@PathVariable Integer id) {
        Sensor existingSensor = sensorService.getSensorById(id);
        if (existingSensor == null) {
            return ResponseEntity.notFound().build();
        }
        sensorService.deleteSensor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/name")
    public List<Sensor> searchSensorsByName(@RequestParam String name) {
        return sensorService.searchSensorsByName(name);
    }

    @GetMapping("/search/model")
    public List<Sensor> searchSensorsByModel(@RequestParam String model) {
        return sensorService.searchSensorsByModel(model);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createSensor(@RequestBody Sensor sensor) {
        String name = sensor.getName();
        if (name.isEmpty() || name.length() < 3 || name.length() > 30) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Name must be between 3 and 30 characters and not empty");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        String model = sensor.getModel();
        if (model.isEmpty() || model.length() > 15) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Name must be 15 characters and not empty");
            return ResponseEntity.badRequest().body(errorResponse);
        }

        Range range = sensor.getRange();
        if (range.getFrom() >= range.getTo() || range.getFrom() <= 0) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "range must be positive ,and from must be less than to ");
            return ResponseEntity.badRequest().body(errorResponse);

        }

        Type type = sensor.getType();
        if (typeService.searchTypeByType(type.getType()).isEmpty()) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "is null ");
            return ResponseEntity.badRequest().body(errorResponse);

        }

        List<Type> typeList = typeService.searchTypeByType(type.getType());
        sensor.setType(typeList.get(0));


        Unit unit = sensor.getUnit();
        if (unitService.searchUnitByUnit(unit.getUnit()).isEmpty()) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "is null ");
            return ResponseEntity.badRequest().body(errorResponse);

        }

        List<Unit> unitList = unitService.searchUnitByUnit(unit.getUnit());
        sensor.setUnit(unitList.get(0));

        String location = sensor.getLocation();
        if (location.length() > 40) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", " Location must be  40 characters or less ");
            return ResponseEntity.badRequest().body(errorResponse);


        }


        String description = sensor.getDescription();
        if (description.length() > 200) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", " description must be  200  characters or less ");
            return ResponseEntity.badRequest().body(errorResponse);


        }

        Sensor createdSensor = sensorService.saveSensor(sensor);
        return ResponseEntity.ok(createdSensor);


    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSensor(@PathVariable Integer id, @RequestBody Sensor sensor) {


        String name = sensor.getName();
        if (name.isEmpty() || name.length() < 3 || name.length() > 30) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Name must be between 3 and 30 characters and not empty");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        String model = sensor.getModel();
        if (model.isEmpty() || model.length() > 15) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Name must be 15 characters and not empty");
            return ResponseEntity.badRequest().body(errorResponse);
        }

        Range range = sensor.getRange();
        if (range.getFrom() >= range.getTo() || range.getFrom() <= 0) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "range must be positive ,and from must be less than to ");
            return ResponseEntity.badRequest().body(errorResponse);

        }

        Type type = sensor.getType();
        if (typeService.searchTypeByType(type.getType()).isEmpty()) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "is null ");
            return ResponseEntity.badRequest().body(errorResponse);

        }

        List<Type> typeList = typeService.searchTypeByType(type.getType());
        sensor.setType(typeList.get(0));


        Unit unit = sensor.getUnit();
        if (unitService.searchUnitByUnit(unit.getUnit()).isEmpty()) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "is null ");
            return ResponseEntity.badRequest().body(errorResponse);

        }

        List<Unit> unitList = unitService.searchUnitByUnit(unit.getUnit());
        sensor.setUnit(unitList.get(0));

        String location = sensor.getLocation();
        if (location.length() > 40) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", " Location must be  40 characters or less ");
            return ResponseEntity.badRequest().body(errorResponse);


        }


        String description = sensor.getDescription();
        if (description.length() > 200) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", " description must be  200  characters or less ");
            return ResponseEntity.badRequest().body(errorResponse);


        }

        sensor.setId(id);
        Sensor updatedSensor = sensorService.saveSensor(sensor);


        return updatedSensor != null ? ResponseEntity.ok(updatedSensor) : ResponseEntity.notFound().build();
    }


}
