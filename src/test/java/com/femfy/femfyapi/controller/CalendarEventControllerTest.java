package com.femfy.femfyapi.controller;

import com.femfy.femfyapi.service.ICalendarEventService;
import dto.CalendarEventDTO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class CalendarEventControllerTest {

    @InjectMocks
    private CalendarEventController controller;

    @Mock
    private ICalendarEventService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetEventsByUserSuccess() {
        Long userId = 1L;
        List<CalendarEventDTO> mockEvents = new ArrayList<>();
        mockEvents.add(new CalendarEventDTO());
        when(service.getCalendarEventByUser(userId)).thenReturn(mockEvents);

        ResponseEntity<List<CalendarEventDTO>> response = controller.getEventsByUser(userId);

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(mockEvents, response.getBody())
        );
    }

    @Test
    void testGetEventsByUserNoEvents() {
        Long userId = 1L;
        when(service.getCalendarEventByUser(userId)).thenReturn(new ArrayList<>());

        ResponseEntity<List<CalendarEventDTO>> response = controller.getEventsByUser(userId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetEventByIdSuccess() {
        Long eventId = 1L;
        CalendarEventDTO mockEvent = new CalendarEventDTO();
        when(service.getCalendarEvent(eventId)).thenReturn(Optional.of(mockEvent));

        ResponseEntity<CalendarEventDTO> response = controller.getEventById(eventId);

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(mockEvent, response.getBody())
        );
    }

    @Test
    void testGetEventByIdNotFound() {
        Long eventId = 1L;
        when(service.getCalendarEvent(eventId)).thenReturn(Optional.empty());

        ResponseEntity<CalendarEventDTO> response = controller.getEventById(eventId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetAllEvents() {
        List<CalendarEventDTO> mockEvents = new ArrayList<>();
        when(service.getCalendarEvents()).thenReturn(mockEvents);

        ResponseEntity<List<CalendarEventDTO>> response = controller.getAllEvents();

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(mockEvents, response.getBody())
        );
    }

    @Test
    void testCreateEvent() {
        CalendarEventDTO eventDTO = new CalendarEventDTO();
        when(service.saveCalendarEvent(any(CalendarEventDTO.class))).thenReturn(eventDTO);

        ResponseEntity<CalendarEventDTO> response = controller.createEvent(eventDTO);

        assertAll(
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode()),
                () -> assertEquals(eventDTO, response.getBody())
        );
    }

    @Test
    void testUpdateEventSuccess() {
        CalendarEventDTO eventDTO = new CalendarEventDTO();
        when(service.updateCalendarEvent(eventDTO)).thenReturn(eventDTO);

        ResponseEntity<CalendarEventDTO> response = controller.updateEvent(eventDTO);

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(eventDTO, response.getBody())
        );
    }

    @Test
    void testUpdateEventNotFound() {
        CalendarEventDTO eventDTO = new CalendarEventDTO();
        when(service.updateCalendarEvent(eventDTO)).thenReturn(null);

        ResponseEntity<CalendarEventDTO> response = controller.updateEvent(eventDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeleteEvent() {
        Long eventId = 1L;
        doNothing().when(service).deleteCalendarEvent(eventId);

        ResponseEntity<Void> response = controller.deleteEvent(eventId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}