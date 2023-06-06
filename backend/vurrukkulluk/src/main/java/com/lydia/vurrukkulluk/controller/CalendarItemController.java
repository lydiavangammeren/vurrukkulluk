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

    @GetMapping
    public List<CalendarItem> getAllItems(){
        return calendarItemService.getAll();
    }

    @PostMapping
    public String saveItem(@RequestBody CalendarItem calendarItem){
        calendarItemService.save(calendarItem);
        return "save calendar item";
    }

}
