package com.femfy.femfyapi.controller;

import com.femfy.femfyapi.service.IQuestionsUserFamilyHistoryService;
import dto.QuestionsUserFamilyHistoryDTO;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuestionsUserFamilyHistoryControllerTest {

    @InjectMocks
    private QuestionsUserFamilyHistoryController controller;

    @Mock
    private IQuestionsUserFamilyHistoryService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetQuestionById() {
        Long questionId = 1L;
        QuestionsUserFamilyHistoryDTO mockQuestion = new QuestionsUserFamilyHistoryDTO();
        mockQuestion.setId(questionId);
        when(service.getQuestionsUserFamilyHistory(questionId)).thenReturn(Optional.of(mockQuestion));

        ResponseEntity<QuestionsUserFamilyHistoryDTO> response = controller.getQuestionById(questionId);

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(mockQuestion, response.getBody())
        );
    }

    @Test
    void testGetQuestionByIdNotFound() {
        Long questionId = 1L;
        when(service.getQuestionsUserFamilyHistory(questionId)).thenReturn(Optional.empty());

        ResponseEntity<QuestionsUserFamilyHistoryDTO> response = controller.getQuestionById(questionId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testGetAllQuestions() {
        List<QuestionsUserFamilyHistoryDTO> mockQuestions = new ArrayList<>();
        when(service.getQuestionsUserFamilyHistories()).thenReturn(mockQuestions);

        ResponseEntity<List<QuestionsUserFamilyHistoryDTO>> response = controller.getAllQuestions();

        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(mockQuestions, response.getBody())
        );
    }

    @Test
    void testCreateQuestion() {
        QuestionsUserFamilyHistoryDTO questionDTO = new QuestionsUserFamilyHistoryDTO();
        when(service.saveQuestionsUserFamilyHistory(any(QuestionsUserFamilyHistoryDTO.class))).thenReturn(questionDTO);

        ResponseEntity<QuestionsUserFamilyHistoryDTO> response = controller.createQuestion(questionDTO);

        assertAll(
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode()),
                () -> assertEquals(questionDTO, response.getBody())
        );
    }

    @Test
    void testUpdateQuestion() {
        QuestionsUserFamilyHistoryDTO questionDTO = new QuestionsUserFamilyHistoryDTO();
        questionDTO.setId(1L);
        when(service.updateQuestionsUserFamilyHistory(questionDTO)).thenReturn(questionDTO);

        ResponseEntity<QuestionsUserFamilyHistoryDTO> response = controller.updateQuestion(questionDTO);

        verify(service, times(1)).updateQuestionsUserFamilyHistory(questionDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testUpdateQuestionNotFound() {
        Long questionId = 1L;
        QuestionsUserFamilyHistoryDTO questionDTO = new QuestionsUserFamilyHistoryDTO();
        questionDTO.setId(questionId);

        when(service.updateQuestionsUserFamilyHistory(questionDTO)).thenReturn(null);

        ResponseEntity<QuestionsUserFamilyHistoryDTO> response = controller.updateQuestion(questionDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeleteQuestion() {
        Long questionId = 1L;
        doNothing().when(service).deleteQuestionsUserFamilyHistory(questionId);

        ResponseEntity<Void> response = controller.deleteQuestion(questionId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}