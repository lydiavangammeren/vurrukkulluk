package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.CalendarItem;
import com.lydia.vurrukkulluk.repository.CalendarItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CalendarItemServiceImpl implements CalendarItemService{

    @Autowired
    CalendarItemRepository calendarItemRepository;


    @Override
    public List<CalendarItem> getAll() {
        return calendarItemRepository.findAll();
    }

    @Override
    public List<CalendarItem> getAllFromTodayOn() {
        return  calendarItemRepository.findAllAfterToday();
    }

    @Override
    public CalendarItem save(CalendarItem calendarItem) {
        return calendarItemRepository.save(calendarItem);
    }

    @Override
    public void deleteById(int id) {
        calendarItemRepository.deleteById(id);
    }

}
