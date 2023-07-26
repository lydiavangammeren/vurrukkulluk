package com.lydia.vurrukkulluk.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class CalendarItemTest {

    static DateTimeFormatter formatter;
    @BeforeAll
    static void setup(){
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    @Test
    void loadAndRead(){

        CalendarItem calendarItem = new CalendarItem();
        calendarItem.setDate(LocalDateTime.parse("2023-06-07 14:00",formatter));
        calendarItem.setDescription("Een online workshop");
        calendarItem.setTitle("Vegetarisch koken");
        calendarItem.setId(123);

        assertEquals(LocalDateTime.parse("2023-06-07 14:00",formatter),calendarItem.getDate());
        assertEquals("Een online workshop",calendarItem.getDescription());
        assertEquals("Vegetarisch koken",calendarItem.getTitle());
        assertEquals(123,calendarItem.getId());

    }

    @Test
    void emptyWhenNotFilled(){
        CalendarItem calendarItem = new CalendarItem();

        assertEquals(0,calendarItem.getId());
        assertNull(calendarItem.getDate());
        assertNull(calendarItem.getDescription());
        assertNull(calendarItem.getTitle());

    }

    @Test
    void allArgsConstructor(){
        CalendarItem calendarItem = new CalendarItem(123,"Vegetarisch koken","Een online workshop"
                ,LocalDateTime.parse("2023-06-07 14:00",formatter));

        assertEquals(LocalDateTime.parse("2023-06-07 14:00",formatter),calendarItem.getDate());
        assertEquals("Een online workshop",calendarItem.getDescription());
        assertEquals("Vegetarisch koken",calendarItem.getTitle());
        assertEquals(123,calendarItem.getId());
    }
}