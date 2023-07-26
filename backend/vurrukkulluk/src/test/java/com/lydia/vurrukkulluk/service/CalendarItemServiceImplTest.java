package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.CalendarItem;
import com.lydia.vurrukkulluk.repository.CalendarItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalendarItemServiceImplTest {

    @Mock
    CalendarItem calendarItem;
    @Mock
    CalendarItemRepository repository;
    CalendarItemServiceImpl calendarItemService;

    @BeforeEach
    void setup(){
        assertNotNull(repository);
        calendarItemService = new CalendarItemServiceImpl(repository);
    }

    @Test
    void getAll() {
        List<CalendarItem> calendarItems = new ArrayList<>();
        calendarItems.add(calendarItem);
        calendarItems.add(calendarItem);
        calendarItems.add(calendarItem);

        when(repository.findAll()).thenReturn(calendarItems);

        assertEquals(calendarItems,calendarItemService.getAll());
        verify(repository).findAll();


    }

    @Test
    void save() {
        when(repository.save(calendarItem)).thenReturn(calendarItem);

        assertEquals(calendarItem,calendarItemService.save(calendarItem));
        verify(repository).save(calendarItem);

    }

    @Test
    void deleteById() {
        calendarItemService.deleteById(1);
        verify(repository).deleteById(1);
    }
}