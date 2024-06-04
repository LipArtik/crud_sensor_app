package com.example.demo.repository;

import com.example.demo.model.Type;
import com.example.demo.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Integer> {
   List<Unit> findByUnitContainingIgnoreCase(String unit);
}
