package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.model.Article;
import com.lydia.vurrukkulluk.model.CalendarItem;
import com.lydia.vurrukkulluk.service.CalendarItemService;
import com.lydia.vurrukkulluk.util.SecurityUtil;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
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
    @GetMapping("/fromtoday")
    public List<CalendarItem> getAllItemsFromTodayOn(){
        return calendarItemService.getAllFromTodayOn();
    }
    @PostMapping()
    public ResponseEntity<String> saveCalendarItem(@RequestBody CalendarItem calendarItem){
        if (!securityUtil.isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("not authorized");
        }
        try {

            calendarItemService.save(calendarItem);
        } catch (ConstraintViolationException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body("saved calendar item");

    }

    @PutMapping()
    public ResponseEntity<String> updateCalendarItem(@RequestBody CalendarItem calendarItem){
        if (!securityUtil.isAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("not authorized");
        }
        calendarItemService.save(calendarItem);
        return ResponseEntity.status(HttpStatus.OK).body("updated calendar item");

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCalendarItem(@PathVariable int id){
        if (!securityUtil.isAdmin()) {
            return ResponseEntity.status(HttpStatus.OK).body("not authorized");
        }
        calendarItemService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("deleted calendar item");

    }
}
