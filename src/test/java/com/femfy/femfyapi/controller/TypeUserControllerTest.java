package com.femfy.femfyapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.femfy.femfyapi.service.TypeUserService;

import dto.TypeUserDTO;

class TypeUserControllerTest {

    private TypeUserController controller;
    private TypeUserService userService;

    @BeforeEach
    void setUp() {
        userService = mock(TypeUserService.class);
        controller = new TypeUserController(userService);
    }

    @Test
    void getTypeUserById_ReturnsOk() {
        Long typeUserId = 1L;
        TypeUserDTO typeUser = new TypeUserDTO();
        typeUser.setIdTypeUser(typeUserId);
        when(userService.getTypeUser(typeUserId)).thenReturn(typeUser);

        ResponseEntity<TypeUserDTO> response = controller.getTypeUserById(typeUserId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(typeUser, response.getBody());
    }

    @Test
    void getTypeUserById_ReturnsNotFound() {
        Long typeUserId = 1L;
        TypeUserDTO typeUser = new TypeUserDTO();
        when(userService.getTypeUser(typeUserId)).thenReturn(typeUser);

        ResponseEntity<TypeUserDTO> response = controller.getTypeUserById(typeUserId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(typeUser, response.getBody());
    }

    @Test
    void getTypeUsers_ReturnsOk() {
        List<TypeUserDTO> typeUsers = new ArrayList<>();
        typeUsers.add(new TypeUserDTO());
        when(userService.getTypeUsers()).thenReturn(typeUsers);

        ResponseEntity<List<TypeUserDTO>> response = controller.getTypeUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(typeUsers, response.getBody());
    }

    @Test
    void getTypeUsers_ReturnsNotFound() {
        List<TypeUserDTO> typeUsers = new ArrayList<>();
        when(userService.getTypeUsers()).thenReturn(typeUsers);

        ResponseEntity<List<TypeUserDTO>> response = controller.getTypeUsers();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(typeUsers, response.getBody());
    }

    @Test
    void saveTypeUser_ReturnsCreated() {
        TypeUserDTO typeUserDTO = new TypeUserDTO();
        when(userService.saveTypeUser(typeUserDTO)).thenReturn(typeUserDTO);

        ResponseEntity<TypeUserDTO> response = controller.saveTypeUser(typeUserDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(typeUserDTO, response.getBody());
    }

    @Test
    void updateTypeUser_ReturnsOk() {
        TypeUserDTO typeUserDTO = new TypeUserDTO();
        when(userService.updateTypeUser(typeUserDTO)).thenReturn(typeUserDTO);

        ResponseEntity<TypeUserDTO> response = controller.updateTypeUser(typeUserDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(typeUserDTO, response.getBody());
    }

    @Test
    void deleteTypeUser_ReturnsOk() {
        Long typeUserId = 1L;
        when(userService.deleteTypeUser(typeUserId)).thenReturn("OK");

        ResponseEntity<String> response = controller.deleteTypeUser(typeUserId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("TypeUser delete OK", response.getBody());
    }

    @Test
    void deleteTypeUser_ReturnsNotFound() {
        Long typeUserId = 1L;
        when(userService.deleteTypeUser(typeUserId)).thenReturn("NotFound");

        ResponseEntity<String> response = controller.deleteTypeUser(typeUserId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("TypeUser not found", response.getBody());
    }
}