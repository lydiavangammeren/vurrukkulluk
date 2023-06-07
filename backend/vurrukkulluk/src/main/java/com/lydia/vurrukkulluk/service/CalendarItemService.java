package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.CalendarItem;

import java.util.List;

public interface CalendarItemService {

    List<CalendarItem> getAll();
    void save(CalendarItem calendarItem);

    void delete(CalendarItem calendarItem);
}
