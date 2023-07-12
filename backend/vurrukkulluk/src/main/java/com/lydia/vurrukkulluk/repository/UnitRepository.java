package com.lydia.vurrukkulluk.repository;

import com.lydia.vurrukkulluk.model.Recipe;
import com.lydia.vurrukkulluk.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepository extends JpaRepository<Unit,Integer>  {
}
