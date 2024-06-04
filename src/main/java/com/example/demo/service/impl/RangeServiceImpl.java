package com.example.demo.service.impl;

import com.example.demo.model.Range;
import com.example.demo.repository.RangeRepository;
import com.example.demo.service.apps.RangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RangeServiceImpl implements RangeService {

    @Autowired
    private RangeRepository rangeRepository;

    @Override
    public List<Range> getAllRanges() {
        return rangeRepository.findAll();
    }

    @Override
    public Range getRangeById(Integer id) {
        return rangeRepository.findById(id).orElse(null);
    }

    @Override
    public Range saveRange(Range range) {
        return rangeRepository.save(range);
    }

    @Override
    public void deleteRange(Integer id) {
        rangeRepository.deleteById(id);
    }
}
