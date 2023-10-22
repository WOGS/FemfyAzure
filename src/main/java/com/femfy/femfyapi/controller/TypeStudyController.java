package com.femfy.femfyapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.femfy.femfyapi.service.TypeStudyService;

import dto.TypeStudyDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/typestudy", produces = MediaType.APPLICATION_JSON_VALUE)
public class TypeStudyController {
	
    private TypeStudyService typeStudyService;
    
    @Autowired
    public TypeStudyController(TypeStudyService typeUserService) {
        this.typeStudyService = typeUserService;
    }

    @Operation(summary = "Obtener un tipo de estudio por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta. Parámetros inválidos",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Tipo de usuario no encontrado",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/{typeStudyId}")
    public ResponseEntity<TypeStudyDTO> getTypeUserById(@PathVariable("typeStudyId") Long typeStudyId) {
    	TypeStudyDTO typeStudy = this.typeStudyService.getTypeStudy(typeStudyId);
        if (typeStudy.getIdTypeStudy() != null) {
            return new ResponseEntity<>(typeStudy, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(typeStudy, HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Obtener todos los tipos de estudios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta. Parámetros inválidos",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Tipos de usuario no encontrados",
                    content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/getTypeStudys")
    public ResponseEntity<List<TypeStudyDTO>> getTypeStudy() {
        List<TypeStudyDTO> typeUsers = this.typeStudyService.getTypeStudies();
        if (!typeUsers.isEmpty()) {
            return new ResponseEntity<>(typeUsers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(typeUsers, HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Crear un nuevo tipo de estudio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tipo de usuario creado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta. Parámetros inválidos",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping("/createTypeStudy")
    public ResponseEntity<TypeStudyDTO> saveTypeStudy(@RequestBody TypeStudyDTO typeStudyDTO) {
    	typeStudyDTO = this.typeStudyService.saveTypeStudy(typeStudyDTO);
        return new ResponseEntity<>(typeStudyDTO, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un tipo de usuario existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta. Parámetros inválidos",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Tipo de usuario no encontrado",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping("/updateTypeStudy")
    public ResponseEntity<TypeStudyDTO> updateTypeUser(@RequestBody TypeStudyDTO typeStudyDTO) {
    	typeStudyDTO = this.typeStudyService.updateTypeStudy(typeStudyDTO);
        return new ResponseEntity<>(typeStudyDTO, HttpStatus.OK);
    }

    @Operation(summary = "Eliminar un tipo de usuario por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de usuario eliminado",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Solicitud incorrecta. Parámetros inválidos",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Tipo de usuario no encontrado",
                    content = {@Content(mediaType = "application/json")})
    })
    @DeleteMapping("/delete/{typeStudyId}")
    public ResponseEntity<String> deleteTypeUser(@PathVariable("typeStudyId") Long typeStudyId) {
        String responseDelete = this.typeStudyService.deleteTypeStudy(typeStudyId);
        if (responseDelete.equalsIgnoreCase("OK")) {
            return new ResponseEntity<>("TypeUser delete OK", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("TypeUser not found", HttpStatus.NOT_FOUND);
        }
    }
}
