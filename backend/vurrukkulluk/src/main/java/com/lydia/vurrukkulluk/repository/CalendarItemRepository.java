package com.lydia.vurrukkulluk.repository;

import com.lydia.vurrukkulluk.model.CalendarItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarItemRepository extends JpaRepository<CalendarItem,Integer> {

    @Query("SELECT c FROM CalendarItem c WHERE c.date > NOW() ORDER BY c.date")
    List<CalendarItem> findAllAfterToday();
}
