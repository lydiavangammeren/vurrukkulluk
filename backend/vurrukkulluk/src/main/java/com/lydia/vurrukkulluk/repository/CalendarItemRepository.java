package com.lydia.vurrukkulluk.repository;

import com.lydia.vurrukkulluk.model.CalendarItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarItemRepository extends JpaRepository<CalendarItem,Integer> {

}
