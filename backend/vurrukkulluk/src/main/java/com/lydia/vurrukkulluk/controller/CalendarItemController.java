package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.model.CalendarItem;
import com.lydia.vurrukkulluk.service.CalendarItemService;
import com.lydia.vurrukkulluk.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/calendar")
@CrossOrigin
public class CalendarItemController {

    @Autowired
    private CalendarItemService calendarItemService;
    @Autowired
    private SecurityUtil securityUtil;

    @GetMapping()
    public List<CalendarItem> getAllItems(){
        return calendarItemService.getAll();
    }

    @PostMapping()
    public String saveCalendarItem(@RequestBody CalendarItem calendarItem){
        if (!securityUtil.isAdmin()) {
            return "not authorized";
        }
        calendarItemService.save(calendarItem);
        return "saved calendar item";

    }

    @PutMapping()
    public String updateCalendarItem(@RequestBody CalendarItem calendarItem){
        if (securityUtil.isAdmin()) {
            return "not authorized";
        }
        calendarItemService.save(calendarItem);
        return "updated calendar item";

    }
    @DeleteMapping("/{id}")
    public String deleteCalendarItem(@PathVariable int id){
        if (securityUtil.isAdmin()) {
            return "not authorized";
        }
        calendarItemService.deleteById(id);
        return "deleted calendar item";

    }
}
