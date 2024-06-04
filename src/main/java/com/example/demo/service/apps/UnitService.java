package com.example.demo.service.apps;

import com.example.demo.model.Type;
import com.example.demo.model.Unit;

import java.util.List;

public interface UnitService {
    List<Unit> getAllUnits();
    Unit getUnitById(Integer id);
    Unit saveUnit(Unit unit);
    void deleteUnit(Integer id);
    List<Unit> searchUnitByUnit(String unit);
}
