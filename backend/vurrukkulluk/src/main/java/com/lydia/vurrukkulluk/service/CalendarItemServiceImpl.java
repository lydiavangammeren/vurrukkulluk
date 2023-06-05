package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.CalendarItem;
import com.lydia.vurrukkulluk.repository.CalendarItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarItemServiceImpl implements CalendarItemService{

    @Autowired
    CalendarItemRepository calendarItemRepository;


    @Override
    public List<CalendarItem> getAll() {
        return calendarItemRepository.findAll();
    }

    @Override
    public void save(CalendarItem calendarItem) {
        calendarItemRepository.save(calendarItem);
    }
}
