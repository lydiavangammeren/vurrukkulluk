package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.dto.UnitDto;
import com.lydia.vurrukkulluk.model.Unit;

import java.util.List;

public interface UnitService {
    Unit save(Unit unit);

    Unit getByName(String name);
    List<Unit> getAll();
}
