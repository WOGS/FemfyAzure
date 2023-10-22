package com.femfy.femfyapi.controller;

import dto.CalendarEventDTO;
import com.femfy.femfyapi.service.ICalendarEventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/calendarEvent")
public class CalendarEventController {

    private final ICalendarEventService calendarEventService;

    @Autowired
    public CalendarEventController(ICalendarEventService calendarEventService) {
        this.calendarEventService = calendarEventService;
    }

    @Operation(summary = "Obtener eventos de calendario por usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Pregunta sobre menstruación no encontrada",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/getEventsByUser/{userId}")
    public ResponseEntity<List<CalendarEventDTO>> getEventsByUser(@PathVariable("userId") Long userId) {
        List<CalendarEventDTO> events = calendarEventService.getCalendarEventByUser(userId);
        if (!events.isEmpty()) {
            return ResponseEntity.ok(events);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Obtener un evento de calendario por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Pregunta sobre menstruación no encontrada",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/{eventId}")
    public ResponseEntity<CalendarEventDTO> getEventById(@PathVariable("eventId") Long eventId) {
        Optional<CalendarEventDTO> event = calendarEventService.getCalendarEvent(eventId);
        return event.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener todos los evento de calendario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Pregunta sobre menstruación no encontrada",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/getAllEvents")
    public ResponseEntity<List<CalendarEventDTO>> getAllEvents() {
        List<CalendarEventDTO> events = calendarEventService.getCalendarEvents();
        return ResponseEntity.ok(events);
    }

    @Operation(summary = "Crear un evento de calendario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Pregunta sobre menstruación no encontrada",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping("/createEvent")
    public ResponseEntity<CalendarEventDTO> createEvent(@RequestBody CalendarEventDTO eventDTO) {
        CalendarEventDTO createdEvent = calendarEventService.saveCalendarEvent(eventDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
    }

    @Operation(summary = "Actualizar un evento de calendario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Pregunta sobre menstruación no encontrada",
                    content = {@Content(mediaType = "application/json")})
    })
    @PutMapping("/updateEvent")
    public ResponseEntity<CalendarEventDTO> updateEvent(@RequestBody CalendarEventDTO eventDTO) {
        if (eventDTO == null || eventDTO.getId() == null) {
            return ResponseEntity.notFound().build();
        }

        CalendarEventDTO updatedEvent = calendarEventService.updateCalendarEvent(eventDTO);

        if (updatedEvent == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(updatedEvent);
    }

    @Operation(summary = "Eliminar un evento de calendario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Pregunta sobre menstruación no encontrada",
                    content = {@Content(mediaType = "application/json")})
    })
    @DeleteMapping("/delete/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable("eventId") Long eventId) {
        calendarEventService.deleteCalendarEvent(eventId);
        return ResponseEntity.noContent().build();
    }
}
