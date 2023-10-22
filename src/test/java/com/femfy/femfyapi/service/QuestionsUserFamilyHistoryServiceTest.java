package com.femfy.femfyapi.service;

import com.femfy.femfyapi.entity.QuestionsUserFamilyHistory;
import com.femfy.femfyapi.entity.User;
import com.femfy.femfyapi.repository.QuestionsUserFamilyHistoryRepository;
import dto.QuestionsUserFamilyHistoryDTO;
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

class QuestionsUserFamilyHistoryServiceTest {

    @Mock
    private QuestionsUserFamilyHistoryRepository repository;

    @InjectMocks
    private QuestionsUserFamilyHistoryService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetQuestionsUserFamilyHistoriesWithData() {
        QuestionsUserFamilyHistory familyHistory = new QuestionsUserFamilyHistory();
        familyHistory.setId(1L);

        User user = new User();
        user.setId(1L);
        familyHistory.setUser(user);

        List<QuestionsUserFamilyHistory> familyHistoryList = Collections.singletonList(familyHistory);
        when(repository.findAll()).thenReturn(familyHistoryList);

        List<QuestionsUserFamilyHistoryDTO> result = service.getQuestionsUserFamilyHistories();

        assertNotNull(result);
        assertEquals(1, result.size());

        QuestionsUserFamilyHistoryDTO dto = result.get(0);
        assertEquals(1L, dto.getId());
        assertEquals(1L, dto.getUserId());
    }

    @Test
    void testGetQuestionsUserFamilyHistoriesWithNoData() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        List<QuestionsUserFamilyHistoryDTO> result = service.getQuestionsUserFamilyHistories();

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetQuestionsUserFamilyHistoryFound() {
        Long id = 1L;

        User user = new User();
        user.setId(1L);

        QuestionsUserFamilyHistory familyHistory = new QuestionsUserFamilyHistory();
        familyHistory.setId(id);
        familyHistory.setUser(user);

        when(repository.findById(id)).thenReturn(Optional.of(familyHistory));

        Optional<QuestionsUserFamilyHistoryDTO> result = service.getQuestionsUserFamilyHistory(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
    }

    @Test
    void testGetQuestionsUserFamilyHistoryNotFound() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        Optional<QuestionsUserFamilyHistoryDTO> result = service.getQuestionsUserFamilyHistory(id);

        assertFalse(result.isPresent());
    }

    @Test
    void testSaveQuestionsUserFamilyHistory() {
        QuestionsUserFamilyHistoryDTO dtoToSave = new QuestionsUserFamilyHistoryDTO();

        User user = new User();
        user.setId(1L);

        dtoToSave.setUserId(user.getId());

        QuestionsUserFamilyHistory savedEntity = new QuestionsUserFamilyHistory();
        savedEntity.setId(1L);
        savedEntity.setUser(user);
        copyProperties(dtoToSave, savedEntity);

        when(repository.save(any(QuestionsUserFamilyHistory.class))).thenReturn(savedEntity);

        QuestionsUserFamilyHistoryDTO result = service.saveQuestionsUserFamilyHistory(dtoToSave);

        assertNotNull(result.getId());
        assertEquals(user.getId(), result.getUserId());
    }

    @Test
    void testUpdateQuestionsUserFamilyHistory() {
        Long idToUpdate = 1L;

        User user = new User();
        user.setId(1L);

        QuestionsUserFamilyHistory existingEntity = new QuestionsUserFamilyHistory();
        existingEntity.setId(idToUpdate);

        existingEntity.setUser(user);

        QuestionsUserFamilyHistoryDTO updatedDTO = new QuestionsUserFamilyHistoryDTO();
        updatedDTO.setId(idToUpdate);

        updatedDTO.setUserId(user.getId());
        copyProperties(updatedDTO, existingEntity);

        when(repository.findById(idToUpdate)).thenReturn(Optional.of(existingEntity));
        when(repository.save(any(QuestionsUserFamilyHistory.class))).thenReturn(existingEntity);

        QuestionsUserFamilyHistoryDTO result = service.updateQuestionsUserFamilyHistory(updatedDTO);

        assertEquals(idToUpdate, result.getId());
        assertEquals(user.getId(), result.getUserId());
    }

    @Test
    void testUpdateQuestionsUserFamilyHistoryNotFound() {
        Long idToUpdate = 1L;
        QuestionsUserFamilyHistoryDTO updatedDTO = new QuestionsUserFamilyHistoryDTO();
        updatedDTO.setId(idToUpdate);

        when(repository.findById(idToUpdate)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.updateQuestionsUserFamilyHistory(updatedDTO));
    }

    @Test
    void testDeleteQuestionsUserFamilyHistory() {
        Long idToDelete = 1L;

        doNothing().when(repository).deleteById(idToDelete);

        assertDoesNotThrow(() -> service.deleteQuestionsUserFamilyHistory(idToDelete));

        verify(repository, times(1)).deleteById(idToDelete);
    }

    private void copyProperties(QuestionsUserFamilyHistoryDTO source, QuestionsUserFamilyHistory target) {
        if (source.isBreastCancer()) {
            target.setBreastCancer(source.isBreastCancer());
        }
        if (source.isOvarianCancer()) {
            target.setOvarianCancer(source.isOvarianCancer());
        }
        if (source.isEndometriosis()) {
            target.setEndometriosis(source.isEndometriosis());
        }
        if (source.isUterineFibroids()) {
            target.setUterineFibroids(source.isUterineFibroids());
        }
        if (source.isSop()) {
            target.setSop(source.isSop());
        }
        if (source.isEarlyMenopause()) {
            target.setEarlyMenopause(source.isEarlyMenopause());
        }
    }
}