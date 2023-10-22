package com.femfy.femfyapi.service;

import com.femfy.femfyapi.entity.CalendarEvent;
import com.femfy.femfyapi.entity.User;
import com.femfy.femfyapi.exception.EntityNotFoundException;
import com.femfy.femfyapi.repository.CalendarEventRepository;
import dto.CalendarEventDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CalendarEventServiceTest {

    @Mock
    private CalendarEventRepository repository;

    @InjectMocks
    private CalendarEventService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetCalendarEventsWithData() {
        CalendarEvent event = new CalendarEvent();
        event.setId(1L);

        User user = new User();
        user.setId(2L);
        event.setUser(user);

        List<CalendarEvent> events = Collections.singletonList(event);
        when(repository.findAll()).thenReturn(events);

        List<CalendarEventDTO> result = service.getCalendarEvents();

        assertNotNull(result);
        assertEquals(1, result.size());

        CalendarEventDTO dto = result.get(0);
        assertEquals(1L, dto.getId());
        assertEquals(2L, dto.getUserId());
    }

    @Test
    void testGetCalendarEventsWithNoData() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        List<CalendarEventDTO> result = service.getCalendarEvents();

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetCalendarEventFound() {
        Long id = 1L;

        User user = new User();
        user.setId(1L);

        CalendarEvent event = new CalendarEvent();
        event.setId(id);
        event.setUser(user);

        when(repository.findById(id)).thenReturn(Optional.of(event));

        Optional<CalendarEventDTO> result = service.getCalendarEvent(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
    }

    @Test
    void testGetCalendarEventNotFound() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        Optional<CalendarEventDTO> result = service.getCalendarEvent(id);

        assertFalse(result.isPresent());
    }

    @Test
    void testSaveCalendarEvent() {
        CalendarEventDTO dtoToSave = new CalendarEventDTO();

        User user = new User();
        user.setId(1L);

        dtoToSave.setUserId(user.getId());

        CalendarEvent savedEntity = new CalendarEvent();
        savedEntity.setId(1L);
        savedEntity.setUser(user);
        copyProperties(dtoToSave, savedEntity);

        when(repository.save(any(CalendarEvent.class))).thenReturn(savedEntity);

        CalendarEventDTO result = service.saveCalendarEvent(dtoToSave);

        assertNotNull(result.getId());
        assertEquals(user.getId(), result.getUserId());
    }

    @Test
    void testUpdateCalendarEvent() {
        Long idToUpdate = 1L;

        User user = new User();
        user.setId(1L);

        CalendarEvent existingEntity = new CalendarEvent();
        existingEntity.setId(idToUpdate);

        existingEntity.setUser(user);

        CalendarEventDTO updatedDTO = new CalendarEventDTO();
        updatedDTO.setId(idToUpdate);

        updatedDTO.setUserId(user.getId());
        copyProperties(updatedDTO, existingEntity);

        when(repository.findById(idToUpdate)).thenReturn(Optional.of(existingEntity));
        when(repository.save(any(CalendarEvent.class))).thenReturn(existingEntity);

        CalendarEventDTO result = service.updateCalendarEvent(updatedDTO);

        assertEquals(idToUpdate, result.getId());
        assertEquals(user.getId(), result.getUserId());
    }

    @Test
    void testUpdateCalendarEventNotFound() {
        Long idToUpdate = 1L;
        CalendarEventDTO updatedDTO = new CalendarEventDTO();
        updatedDTO.setId(idToUpdate);

        when(repository.findById(idToUpdate)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> service.updateCalendarEvent(updatedDTO));
    }

    @Test
    void testDeleteCalendarEvent() {
        Long idToDelete = 1L;

        doNothing().when(repository).deleteById(idToDelete);

        assertDoesNotThrow(() -> service.deleteCalendarEvent(idToDelete));

        verify(repository, times(1)).deleteById(idToDelete);
    }

    private void copyProperties(CalendarEventDTO source, CalendarEvent target) {
        if (source.getTitle() != null) {
            target.setTitle(source.getTitle());
        }
        if (source.getDateEvent() != null) {
            target.setDateEvent(source.getDateEvent());
        }
        if (source.getHourAlert() != null) {
            target.setHourAlert(source.getHourAlert());
        }
        if (source.getDescription() != null) {
            target.setDescription(source.getDescription());
        }
    }
}