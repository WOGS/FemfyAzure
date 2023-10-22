package com.femfy.femfyapi.service;

import com.femfy.femfyapi.entity.User;

import com.femfy.femfyapi.entity.QuestionsUserMenstruation;
import com.femfy.femfyapi.repository.QuestionsUserMenstruationRepository;
import dto.QuestionsUserMenstruationDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuestionsUserMenstruationServiceTest {

    @Mock
    private QuestionsUserMenstruationRepository repository;

    @InjectMocks
    private QuestionsUserMenstruationService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetQuestionsUserMenstruationsWithData() {
        QuestionsUserMenstruation menstruation = new QuestionsUserMenstruation();
        menstruation.setId(1L);

        User user = new User();
        user.setId(1L);
        menstruation.setUser(user);

        List<QuestionsUserMenstruation> menstruationList = Collections.singletonList(menstruation);
        when(repository.findAll()).thenReturn(menstruationList);

        List<QuestionsUserMenstruationDTO> result = service.getQuestionsUserMenstruations();

        assertNotNull(result);
        assertEquals(1, result.size());

        QuestionsUserMenstruationDTO dto = result.get(0);
        assertEquals(1L, dto.getId());
        assertEquals(1L, dto.getUserId());
    }

    @Test
    void testGetQuestionsUserMenstruationsWithNoData() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        List<QuestionsUserMenstruationDTO> result = service.getQuestionsUserMenstruations();

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetQuestionsUserMenstruationFound() {
        Long id = 1L;

        User user = new User();
        user.setId(1L);

        QuestionsUserMenstruation menstruation = new QuestionsUserMenstruation();
        menstruation.setId(id);
        menstruation.setUser(user);

        when(repository.findById(id)).thenReturn(Optional.of(menstruation));

        Optional<QuestionsUserMenstruationDTO> result = service.getQuestionsUserMenstruation(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
    }

    @Test
    void testGetQuestionsUserMenstruationNotFound() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        Optional<QuestionsUserMenstruationDTO> result = service.getQuestionsUserMenstruation(id);

        assertFalse(result.isPresent());
    }

    @Test
    void testSaveQuestionsUserMenstruation() {
        QuestionsUserMenstruationDTO dtoToSave = new QuestionsUserMenstruationDTO();

        User user = new User();
        user.setId(1L);

        dtoToSave.setUserId(user.getId());

        dtoToSave.setLastTime(new Date(System.currentTimeMillis()));

        QuestionsUserMenstruation savedEntity = new QuestionsUserMenstruation();
        savedEntity.setId(1L);
        savedEntity.setUser(user);
        savedEntity.setLastTime(dtoToSave.getLastTime());

        when(repository.save(any(QuestionsUserMenstruation.class))).thenReturn(savedEntity);

        QuestionsUserMenstruationDTO result = service.saveQuestionsUserMenstruation(dtoToSave);

        assertNotNull(result.getId());
        assertEquals(dtoToSave.getLastTime(), result.getLastTime());
        assertEquals(user.getId(), result.getUserId());
    }

    @Test
    void testUpdateQuestionsUserMenstruation() {
        Long idToUpdate = 1L;

        User user = new User();
        user.setId(1L);

        QuestionsUserMenstruation existingEntity = new QuestionsUserMenstruation();
        existingEntity.setId(idToUpdate);

        existingEntity.setUser(user);

        QuestionsUserMenstruationDTO updatedDTO = new QuestionsUserMenstruationDTO();
        updatedDTO.setId(idToUpdate);

        updatedDTO.setUserId(user.getId());

        updatedDTO.setLastTime(new Date(System.currentTimeMillis()));

        when(repository.findById(idToUpdate)).thenReturn(Optional.of(existingEntity));
        when(repository.save(any(QuestionsUserMenstruation.class))).thenReturn(existingEntity);

        QuestionsUserMenstruationDTO result = service.updateQuestionsUserMenstruation(updatedDTO);

        assertEquals(idToUpdate, result.getId());
        assertEquals(updatedDTO.getLastTime(), result.getLastTime());
        assertEquals(user.getId(), result.getUserId());
    }

    @Test
    void testUpdateQuestionsUserMenstruationNotFound() {
        Long idToUpdate = 1L;
        QuestionsUserMenstruationDTO updatedDTO = new QuestionsUserMenstruationDTO();
        updatedDTO.setId(idToUpdate);

        when(repository.findById(idToUpdate)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.updateQuestionsUserMenstruation(updatedDTO));
    }
}