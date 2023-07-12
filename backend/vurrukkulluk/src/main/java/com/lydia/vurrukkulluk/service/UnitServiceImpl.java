package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Unit;
import com.lydia.vurrukkulluk.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnitServiceImpl implements UnitService{

    @Autowired
    private UnitRepository unitRepository;

    @Override
    public Unit save(Unit unit) {
        unitRepository.save(unit);
        return unit;
    }
}
