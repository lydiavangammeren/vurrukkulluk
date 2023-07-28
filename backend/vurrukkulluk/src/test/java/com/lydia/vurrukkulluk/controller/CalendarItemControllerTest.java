package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.model.CalendarItem;
import com.lydia.vurrukkulluk.service.CalendarItemService;
import com.lydia.vurrukkulluk.util.SecurityUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalendarItemControllerTest {

    @Mock
    CalendarItemService calendarItemService;
    @Mock
    SecurityUtil securityUtil;
    @Mock
    CalendarItem calendarItem;
    @Mock
    List<CalendarItem> calendarItems;

    CalendarItemController controller;
    @BeforeEach
    void makeController(){
        controller = new CalendarItemController(calendarItemService,securityUtil);
    }

    @Test
    void getAllItems() {
        when(calendarItemService.getAll()).thenReturn(calendarItems);
        assertEquals(calendarItems,controller.getAllItems());
    }

    @Test
    void saveCalendarItemWhenAuthorized() {
        when(securityUtil.isAdmin()).thenReturn(true);
        assertEquals(ResponseEntity.status(HttpStatus.OK).body("saved calendar item"),controller.saveCalendarItem(calendarItem));
        verify(calendarItemService).save(calendarItem);
    }
    @Test
    void saveCalendarItemWhenNotAuthorized() {
        when(securityUtil.isAdmin()).thenReturn(false);
        assertEquals(ResponseEntity.status(HttpStatus.FORBIDDEN).body("not authorized"),controller.saveCalendarItem(calendarItem));
        verifyNoInteractions(calendarItemService);

    }
    @Test
    void updateCalendarItemWhenAuthorized() {
        when(securityUtil.isAdmin()).thenReturn(true);
        assertEquals(ResponseEntity.status(HttpStatus.OK).body("updated calendar item"),controller.updateCalendarItem(calendarItem));
        verify(calendarItemService).save(calendarItem);
    }

    @Test
    void updateCalendarItemWhenNotAuthorized() {
        when(securityUtil.isAdmin()).thenReturn(false);
        assertEquals(ResponseEntity.status(HttpStatus.FORBIDDEN).body("not authorized"),controller.updateCalendarItem(calendarItem));
        verifyNoInteractions(calendarItemService);
    }
    @Test
    void deleteCalendarItemWhenAuthorized() {
        when(securityUtil.isAdmin()).thenReturn(true);
        assertEquals(ResponseEntity.status(HttpStatus.OK).body("deleted calendar item"),controller.deleteCalendarItem(1));
        verify(calendarItemService).deleteById(1);
    }
    @Test
    void deleteCalendarItemWhenNotAuthorized() {
        when(securityUtil.isAdmin()).thenReturn(false);
        assertEquals(ResponseEntity.status(HttpStatus.FORBIDDEN).body("not authorized"),controller.updateCalendarItem(calendarItem));
        verifyNoInteractions(calendarItemService);
    }

    @Test
    void getAllItemsFromTodayOn() {
        when(calendarItemService.getAllFromTodayOn()).thenReturn(calendarItems);
        assertEquals(calendarItems,controller.getAllItemsFromTodayOn());
    }
}