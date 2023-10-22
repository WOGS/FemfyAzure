package com.femfy.femfyapi.service;

import com.femfy.femfyapi.entity.CalendarEvent;
import com.femfy.femfyapi.entity.User;
import com.femfy.femfyapi.exception.EntityNotFoundException;
import com.femfy.femfyapi.repository.CalendarEventRepository;
import dto.CalendarEventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CalendarEventService implements ICalendarEventService {

    private final CalendarEventRepository calendarEventRepository;

    @Autowired
    public CalendarEventService(CalendarEventRepository calendarEventRepository) {
        this.calendarEventRepository = calendarEventRepository;
    }

    @Override
    public List<CalendarEventDTO> getCalendarEvents() {
        List<CalendarEvent> calendarEventList = calendarEventRepository.findAll();
        return calendarEventList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CalendarEventDTO> getCalendarEvent(Long id) {
        Optional<CalendarEvent> calendarEvent = calendarEventRepository.findById(id);
        return calendarEvent.map(this::mapToDTO);
    }

    @Override
    public List<CalendarEventDTO> getCalendarEventByUser(Long userId) {
        List<CalendarEvent> calendarEvents = calendarEventRepository.findByUserId(userId);
        return calendarEvents.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CalendarEventDTO saveCalendarEvent(CalendarEventDTO calendarEventDTO) {
        CalendarEvent calendarEvent = mapToEntity(calendarEventDTO);
        calendarEvent = calendarEventRepository.save(calendarEvent);
        return mapToDTO(calendarEvent);
    }

    @Override
    public CalendarEventDTO updateCalendarEvent(CalendarEventDTO calendarEventDTO) {
        Long idToUpdate = calendarEventDTO.getId();
        if (idToUpdate == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo para la actualización");
        }

        CalendarEvent existingEvent = findCalendarEventById(idToUpdate);

        updateCalendarEventFields(existingEvent, calendarEventDTO);

        existingEvent = calendarEventRepository.save(existingEvent);

        return mapToDTO(existingEvent);
    }

    @Override
    public void deleteCalendarEvent(Long id) {
        calendarEventRepository.deleteById(id);
    }

    private CalendarEventDTO mapToDTO(CalendarEvent calendarEvent) {
        if (calendarEvent == null) {
            throw new EntityNotFoundException("Evento no encontrado");
        }

        CalendarEventDTO dto = new CalendarEventDTO();
        dto.setId(calendarEvent.getId());

        if (calendarEvent.getUser() != null) {
            dto.setUserId(calendarEvent.getUser().getId());
        }

        dto.setTitle(calendarEvent.getTitle());
        dto.setDateEvent(calendarEvent.getDateEvent());
        dto.setHourAlert(calendarEvent.getHourAlert());
        dto.setDescription(calendarEvent.getDescription());
        return dto;
    }

    private CalendarEvent mapToEntity(CalendarEventDTO dto) {
        User user = new User();
        user.setId(dto.getUserId());

        CalendarEvent calendarEvent = new CalendarEvent();
        calendarEvent.setId(dto.getId());
        calendarEvent.setUser(user);
        calendarEvent.setTitle(dto.getTitle());
        calendarEvent.setDateEvent(dto.getDateEvent());
        calendarEvent.setHourAlert(dto.getHourAlert());
        calendarEvent.setDescription(dto.getDescription());
        return calendarEvent;
    }

    private CalendarEvent findCalendarEventById(Long eventId) {
        return calendarEventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró un evento con el ID: " + eventId));
    }

    private void updateCalendarEventFields(CalendarEvent existingEvent, CalendarEventDTO calendarEventDTO) {
        if(calendarEventDTO.getTitle() != null){
            existingEvent.setTitle(calendarEventDTO.getTitle());
        }
        if(calendarEventDTO.getDateEvent() != null){
            existingEvent.setDateEvent(calendarEventDTO.getDateEvent());
        }
        if(calendarEventDTO.getHourAlert() != null){
            existingEvent.setHourAlert(calendarEventDTO.getHourAlert());
        }
        if(calendarEventDTO.getDescription() != null){
            existingEvent.setDescription(calendarEventDTO.getDescription());
        }
    }
}