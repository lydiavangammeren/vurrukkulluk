package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.model.CalendarItem;
import com.lydia.vurrukkulluk.service.CalendarItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calendar")
@CrossOrigin
public class CalendarItemController {

    @Autowired
    CalendarItemService calendarItemService;

    @GetMapping()
    public List<CalendarItem> getAllItems(){
        return calendarItemService.getAll();
    }

    @PostMapping()
    public String saveCalendarItem(@RequestBody CalendarItem calendarItem){
        calendarItemService.save(calendarItem);
        return "saved calendar item";
    }

    @PutMapping()
    public String updateCalendarItem(@RequestBody CalendarItem calendarItem){
        calendarItemService.save(calendarItem);
        return "updated calendar item";
    }
    @DeleteMapping("/{id}")
    public String deleteCalendarItem(@PathVariable int id){
        calendarItemService.deleteById(id);
        return "deleted calendar item";
    }
}
