package com.example.demo.repository;

import com.example.demo.model.Sensor;
import com.example.demo.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRepository extends JpaRepository<Type,Integer> {
    List<Type> findByTypeContainingIgnoreCase(String type);
}
