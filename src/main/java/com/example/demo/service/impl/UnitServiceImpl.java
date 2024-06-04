package com.example.demo.service.impl;

import com.example.demo.model.Unit;
import com.example.demo.repository.UnitRepository;
import com.example.demo.service.apps.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitServiceImpl implements UnitService {

    @Autowired
    private UnitRepository unitRepository;

    @Override
    public List<Unit> getAllUnits() {
        return unitRepository.findAll();
    }

    @Override
    public Unit getUnitById(Integer id) {
        return unitRepository.findById(id).orElse(null);
    }

    @Override
    public Unit saveUnit(Unit unit) {
        return unitRepository.save(unit);
    }

    @Override
    public void deleteUnit(Integer id) {
        unitRepository.deleteById(id);
    }

    @Override
    public List<Unit> searchUnitByUnit(String unit) {
        return unitRepository.findByUnitContainingIgnoreCase(unit);
    }
}
