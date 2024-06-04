package com.example.demo.service.impl;

import com.example.demo.model.Type;
import com.example.demo.repository.TypeRepository;
import com.example.demo.service.apps.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Override
    public List<Type> getAllTypes() {
        return typeRepository.findAll();
    }

    @Override
    public Type getTypeById(Integer id) {
        return typeRepository.findById(id).orElse(null);
    }

    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public void deleteType(Integer id) {
        typeRepository.deleteById(id);
    }

    @Override
    public List<Type> searchTypeByType(String type) {
        return typeRepository.findByTypeContainingIgnoreCase(type);
    }
}
