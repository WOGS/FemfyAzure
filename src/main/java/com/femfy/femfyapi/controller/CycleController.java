package com.femfy.femfyapi.controller;


import com.femfy.femfyapi.entity.Cycle;
import com.femfy.femfyapi.entity.ResponseError;
import com.femfy.femfyapi.service.ICycleService;
import dto.CalendarEventDTO;
import dto.CycleDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/cycle", produces = MediaType.APPLICATION_JSON_VALUE)
public class CycleController {

    @Autowired
    ICycleService iCycleService;

    @Operation(summary = "Registra el inicio del nuevo ciclo")
    @ApiResponses(value ={// -
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Unexpected system error",
                    content = {@Content (mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Bad Request. Parametros invalidos",
                    content = {@Content (mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Usuario inexistente",
                    content = {@Content (mediaType = "application/json")})

    })
    @PostMapping("/registerCycleStart")
    public ResponseEntity<?> registerCycleStart(@RequestBody Cycle cycle){
        try{
            return new ResponseEntity<>(iCycleService.registerCycleStart(cycle), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseError(500, e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Registra el fin del nuevo ciclo")
    @ApiResponses(value ={// -
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Unexpected system error",
                    content = {@Content (mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Bad Request. Parametros invalidos",
                    content = {@Content (mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Usuario inexistente",
                    content = {@Content (mediaType = "application/json")})

    })
    @PutMapping("/registerCycleEnd")
    public ResponseEntity<?> registerCycleEnd(@RequestBody Cycle cycle){
        try{
            return new ResponseEntity<>(iCycleService.registerCycleEnd(cycle), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ResponseError(500, e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Devuelve el historial de ciclos de la usuario")
    @ApiResponses(value ={// -
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Unexpected system error",
                    content = {@Content (mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Bad Request. Parametros invalidos",
                    content = {@Content (mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Usuario inexistente",
                    content = {@Content (mediaType = "application/json")})

    })
    @GetMapping("/getCycleHistory/{idUser}")
    public ResponseEntity<?> getCycleHistory(@PathVariable(value = "idUser") Long idUser) {
        try {
            List<CycleDTO> list = iCycleService.getCycleHistory(idUser);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseError(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Operation(summary = "Devuelve el ciclos de la usuario")
    @ApiResponses(value ={// -
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Unexpected system error",
                    content = {@Content (mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Bad Request. Parametros invalidos",
                    content = {@Content (mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Usuario inexistente",
                    content = {@Content (mediaType = "application/json")})

    })
    @GetMapping("/getCycle/{idUser}")
    public ResponseEntity<?> getCycle(@PathVariable(value = "idUser") Long idUser, @RequestParam("dateBeging") String dateBeging ) {
        try {
            CycleDTO dto = iCycleService.getCycleByIdUserAndDateBeging(idUser, dateBeging);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseError(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Eliminar un ciclo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Bad Request. Parametros invalidos",
                    content = {@Content(mediaType = "application/json")})
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCycle(@PathVariable("id") Long id) {
        try {
            Map<String, String> res = iCycleService.deleteCycle(id);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseError(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Modificar un ciclo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respuesta OK",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Error inesperado del sistema",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Bad Request. Parametros invalidos",
                    content = {@Content(mediaType = "application/json")})
    })
    @PutMapping("/updateCycle")
    public ResponseEntity<?> updateCycle(@RequestBody Cycle cycle) {
        try {
            CycleDTO dto = iCycleService.updateCycle(cycle);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseError(500, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
