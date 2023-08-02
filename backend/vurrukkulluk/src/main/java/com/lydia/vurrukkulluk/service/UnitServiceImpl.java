package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Unit;
import com.lydia.vurrukkulluk.repository.UnitRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UnitServiceImpl implements UnitService{

    @Autowired
    private UnitRepository unitRepository;

    @Override
    public Unit save(Unit unit) {
        unitRepository.save(unit);
        return unit;
    }

    @Override
    public Unit getByName(String name) {
        return unitRepository.findByName(name);
    }

    @Override
    public List<Unit> getAll() {
        return unitRepository.findAll();
    }
}
