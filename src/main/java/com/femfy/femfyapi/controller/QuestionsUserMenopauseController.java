package com.femfy.femfyapi.controller;

import dto.QuestionsUserMenopauseDTO;
import com.femfy.femfyapi.service.IQuestionsUserMenopauseService;
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
@RequestMapping("/api/v1/questionsUserMenopause")
public class QuestionsUserMenopauseController {

    private final IQuestionsUserMenopauseService questionsUserMenopauseService;

    @Autowired
    public QuestionsUserMenopauseController(IQuestionsUserMenopauseService questionsUserMenopauseService) {
        this.questionsUserMenopauseService = questionsUserMenopauseService;
    }

    @Operation(summary = "Obtener respuestas básicas sobre las usuarias menopausicas por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Menopausia no encontrada",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/{questionId}")
    public ResponseEntity<QuestionsUserMenopauseDTO> getQuestionById(@PathVariable("questionId") Long questionId) {
        Optional<QuestionsUserMenopauseDTO> question = questionsUserMenopauseService.getQuestionsUserMenopause(questionId);
        return question.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener todas las respuestas básicas sobre las usuarias con menopausia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
    })
    @GetMapping("/getAllQuestions")
    public ResponseEntity<List<QuestionsUserMenopauseDTO>> getAllQuestions() {
        List<QuestionsUserMenopauseDTO> questions = questionsUserMenopauseService.getQuestionsUserMenopause();
        return ResponseEntity.ok(questions);
    }

    @Operation(summary = "Crear una respuesta básica sobre la menopausia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Menopausia creada",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
    })
    @PostMapping("/createQuestion")
    public ResponseEntity<QuestionsUserMenopauseDTO> createQuestion(@RequestBody QuestionsUserMenopauseDTO questionDTO) {
        QuestionsUserMenopauseDTO savedQuestion = questionsUserMenopauseService.saveQuestionsUserMenopause(questionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedQuestion);
    }

    @Operation(summary = "Actualizar una respuesta básica sobre la menopausia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Menopausia actualizada",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Menopausia no encontrada",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
    })
    @PutMapping("/updateQuestion")
    public ResponseEntity<QuestionsUserMenopauseDTO> updateQuestion(@RequestBody QuestionsUserMenopauseDTO questionDTO) {
        if (questionDTO == null || questionDTO.getId() == null) {
            return ResponseEntity.notFound().build();
        }

        QuestionsUserMenopauseDTO updatedQuestion = questionsUserMenopauseService.updateQuestionsUserMenopause(questionDTO);
        if (updatedQuestion == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedQuestion);
    }

    @Operation(summary = "Eliminar respuestas básicas sobre la menopausia por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Menopausia eliminada"),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Menopausia no encontrada",
                    content = {@Content(mediaType = "application/json")})
    })
    @DeleteMapping("/delete/{questionId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable("questionId") Long questionId) {
        questionsUserMenopauseService.deleteQuestionsUserMenopause(questionId);
        return ResponseEntity.noContent().build();
    }
}