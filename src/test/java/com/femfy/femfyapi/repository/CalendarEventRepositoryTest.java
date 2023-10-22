package com.femfy.femfyapi.repository;

import com.femfy.femfyapi.entity.CalendarEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import java.util.Optional;
import java.util.Date;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CalendarEventRepositoryTest {

    @Autowired
    private CalendarEventRepository calendarEventRepository;

    @BeforeEach
    public void setUp() {
        CalendarEvent event = new CalendarEvent();
        event.setTitle("Nuevo evento");
        event.setDateEvent(new Date());
        event.setHourAlert(new Date());
        event.setDescription("Descripcion del evento");

        calendarEventRepository.save(event);
    }

    @Test
    public void testFindById() {
        // Inserta
        CalendarEvent event = new CalendarEvent();
        event.setTitle("Evento 2");
        event.setDateEvent(new Date());
        event.setHourAlert(new Date());
        event.setDescription("Descripcion del evento 2");
        calendarEventRepository.save(event);

        // Busca
        Optional<CalendarEvent> foundEvent = calendarEventRepository.findById(event.getId());

        // Verifica
        assertThat(foundEvent).isPresent();
        assertThat(foundEvent.get().getId()).isEqualTo(event.getId());
    }

    @Test
    public void testFindAll() {
        // Obtiene
        List<CalendarEvent> events = calendarEventRepository.findAll();

        // Inserta
        CalendarEvent event1 = new CalendarEvent();
        event1.setTitle("EEvento 3");
        event1.setDateEvent(new Date());
        event1.setHourAlert(new Date());
        event1.setDescription("Descripcion del evento 3");
        calendarEventRepository.save(event1);

        CalendarEvent event2 = new CalendarEvent();
        event2.setTitle("Evento 4");
        event2.setDateEvent(new Date());
        event2.setHourAlert(new Date());
        event2.setDescription("Descripcion del evento 4");
        calendarEventRepository.save(event2);

        // Obtiene
        List<CalendarEvent> allEvents = calendarEventRepository.findAll();

        // Verifica
        assertThat(allEvents).hasSize(3);
    }

    @Test
    public void testSave() {
        // Crea
        CalendarEvent event = new CalendarEvent();
        event.setTitle("Evento 5");
        event.setDateEvent(new Date());
        event.setHourAlert(new Date());
        event.setDescription("Descripcion del evento 5");
        CalendarEvent savedEvent = calendarEventRepository.save(event);

        // Verifica
        assertThat(savedEvent.getId()).isNotNull();
        assertThat(savedEvent.getTitle()).isEqualTo("Evento 5");
    }

    @Test
    public void testDeleteById() {
        // Inserta
        CalendarEvent event = new CalendarEvent();
        event.setTitle("Evento 6");
        event.setDateEvent(new Date());
        event.setHourAlert(new Date());
        event.setDescription("Descripcion del evento 6");
        calendarEventRepository.save(event);

        // Elimina
        calendarEventRepository.deleteById(event.getId());

        // Verifica
        Optional<CalendarEvent> deletedEvent = calendarEventRepository.findById(event.getId());
        assertThat(deletedEvent).isNotPresent();
    }
}