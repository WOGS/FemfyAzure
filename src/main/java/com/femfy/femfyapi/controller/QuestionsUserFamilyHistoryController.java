package com.femfy.femfyapi.controller;

import dto.QuestionsUserFamilyHistoryDTO;
import com.femfy.femfyapi.service.IQuestionsUserFamilyHistoryService;
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
@RequestMapping("/api/v1/questionsUserFamilyHistory")
public class QuestionsUserFamilyHistoryController {

    private final IQuestionsUserFamilyHistoryService questionsUserFamilyHistoryService;

    @Autowired
    public QuestionsUserFamilyHistoryController(IQuestionsUserFamilyHistoryService questionsUserFamilyHistoryService) {
        this.questionsUserFamilyHistoryService = questionsUserFamilyHistoryService;
    }

    @Operation(summary = "Obtener una respuesta básica sobre historia familiar por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Historia familiar no encontrada",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/{questionId}")
    public ResponseEntity<QuestionsUserFamilyHistoryDTO> getQuestionById(@PathVariable("questionId") Long questionId) {
        Optional<QuestionsUserFamilyHistoryDTO> question = questionsUserFamilyHistoryService.getQuestionsUserFamilyHistory(questionId);
        return question.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener todas las respuestas básicas sobre historia familiar")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
    })
    @GetMapping("/getAllQuestions")
    public ResponseEntity<List<QuestionsUserFamilyHistoryDTO>> getAllQuestions() {
        List<QuestionsUserFamilyHistoryDTO> questions = questionsUserFamilyHistoryService.getQuestionsUserFamilyHistories();
        return ResponseEntity.ok(questions);
    }

    @Operation(summary = "Crear una pregunta sobre historia familiar")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Historia familiar creada",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
    })
    @PostMapping("/createQuestion")
    public ResponseEntity<QuestionsUserFamilyHistoryDTO> createQuestion(@RequestBody QuestionsUserFamilyHistoryDTO questionDTO) {
        QuestionsUserFamilyHistoryDTO savedQuestion = questionsUserFamilyHistoryService.saveQuestionsUserFamilyHistory(questionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedQuestion);
    }

    @Operation(summary = "Actualizar una pregunta sobre historia familiar")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Historia familiar actualizada",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Historia familiar no encontrada",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
    })
    @PutMapping("/updateQuestion")
    public ResponseEntity<QuestionsUserFamilyHistoryDTO> updateQuestion(@RequestBody QuestionsUserFamilyHistoryDTO questionDTO) {
        if (questionDTO == null || questionDTO.getId() == null) {
            return ResponseEntity.notFound().build();
        }

        QuestionsUserFamilyHistoryDTO updatedQuestion = questionsUserFamilyHistoryService.updateQuestionsUserFamilyHistory(questionDTO);
        if (updatedQuestion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedQuestion);
    }

    @Operation(summary = "Eliminar una respuesta básica sobre historia familiar por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Historia familiar eliminada"),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Historia familiar no encontrada",
                    content = {@Content(mediaType = "application/json")})
    })
    @DeleteMapping("/delete/{questionId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable("questionId") Long questionId) {
        questionsUserFamilyHistoryService.deleteQuestionsUserFamilyHistory(questionId);
        return ResponseEntity.noContent().build();
    }
}