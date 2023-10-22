package com.femfy.femfyapi.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import com.femfy.femfyapi.entity.TypeUser;
import com.femfy.femfyapi.repository.TypeUserRepository;

import dto.TypeUserDTO;

class TypeUserServiceTest {

    private TypeUserService typeUserService;
    private TypeUserRepository typeUserRepository;

    @BeforeEach
    void setUp() {
        typeUserRepository = mock(TypeUserRepository.class);
        typeUserService = new TypeUserService(typeUserRepository);
    }

    @Test
    void saveTypeUser_ValidTypeUserDTO_ReturnsTypeUserDTO() {
        TypeUserDTO inputDTO = createTypeUserDTO("Menstruante");
        TypeUser savedTypeUser = createTypeUser(1L, "Menstruante");
        when(typeUserRepository.save(any(TypeUser.class))).thenReturn(savedTypeUser);

        TypeUserDTO resultDTO = typeUserService.saveTypeUser(inputDTO);

        assertTypeUserDTOEquals(savedTypeUser, resultDTO);
    }

    @Test
    void updateTypeUser_ValidTypeUserDTO_ReturnsTypeUserDTO() {
        TypeUserDTO inputDTO = createTypeUserDTO(1L, "Menopausica");
        TypeUser updatedTypeUser = createTypeUser(1L, "Menopausica");
        when(typeUserRepository.save(any(TypeUser.class))).thenReturn(updatedTypeUser);

        TypeUserDTO resultDTO = typeUserService.updateTypeUser(inputDTO);

        assertTypeUserDTOEquals(updatedTypeUser, resultDTO);
    }

    @Test
    void deleteTypeUser_ValidId_ReturnsOk() {
        Long idToDelete = 1L;
        doNothing().when(typeUserRepository).deleteById(idToDelete);

        String result = typeUserService.deleteTypeUser(idToDelete);

        assertEquals("OK", result);
    }

    @Test
    void deleteTypeUser_InvalidId_ReturnsError() {
        Long idToDelete = 1L;
        doThrow(EmptyResultDataAccessException.class).when(typeUserRepository).deleteById(idToDelete);

        String result = typeUserService.deleteTypeUser(idToDelete);

        assertEquals("Error: No se encontró ningún registro con el ID proporcionado.", result);
    }

    @Test
    void getTypeUser_ValidId_ReturnsTypeUserDTO() {
        Long idToRetrieve = 1L;
        TypeUser retrievedTypeUser = createTypeUser(idToRetrieve, "No menstrua - Hormonal");
        when(typeUserRepository.findById(idToRetrieve)).thenReturn(Optional.of(retrievedTypeUser));

        TypeUserDTO resultDTO = typeUserService.getTypeUser(idToRetrieve);

        assertTypeUserDTOEquals(retrievedTypeUser, resultDTO);
    }

    @Test
    void getTypeUser_InvalidId_ReturnsEmptyTypeUserDTO() {
        Long idToRetrieve = 1L;
        when(typeUserRepository.findById(idToRetrieve)).thenReturn(Optional.empty());

        TypeUserDTO resultDTO = typeUserService.getTypeUser(idToRetrieve);

        assertNullTypeUserDTO(resultDTO);
    }

    @Test
    void getTypeUsers_ReturnsListOfTypeUserDTOs() {
        List<TypeUser> typeUserList = new ArrayList<>();
        TypeUser typeUser1 = createTypeUser(1L, "Menstruante");
        TypeUser typeUser2 = createTypeUser(2L, "Menopausica");
        typeUserList.add(typeUser1);
        typeUserList.add(typeUser2);

        when(typeUserRepository.findAll()).thenReturn(typeUserList);

        List<TypeUserDTO> resultDTOList = typeUserService.getTypeUsers();

        assertEquals(2, resultDTOList.size());
        assertTypeUserDTOEquals(typeUser1, resultDTOList.get(0));
        assertTypeUserDTOEquals(typeUser2, resultDTOList.get(1));
    }

    @Test
    void getTypeUsers_ReturnsEmptyList() {
        List<TypeUser> emptyTypeUserList = new ArrayList<>();
        when(typeUserRepository.findAll()).thenReturn(emptyTypeUserList);

        List<TypeUserDTO> resultDTOList = typeUserService.getTypeUsers();

        assertTrue(resultDTOList.isEmpty());
    }

    private TypeUserDTO createTypeUserDTO(String description) {
        TypeUserDTO dto = new TypeUserDTO();
        dto.setDescription(description);
        return dto;
    }

    private TypeUserDTO createTypeUserDTO(Long id, String description) {
        TypeUserDTO dto = createTypeUserDTO(description);
        dto.setIdTypeUser(id);
        return dto;
    }

    private TypeUser createTypeUser(Long id, String description) {
        TypeUser typeUser = new TypeUser();
        typeUser.setId(id);
        typeUser.setDescription(description);
        return typeUser;
    }

    private void assertTypeUserDTOEquals(TypeUser expected, TypeUserDTO actual) {
        assertNotNull(actual);
        assertEquals(expected.getId(), actual.getIdTypeUser());
        assertEquals(expected.getDescription(), actual.getDescription());
    }

    private void assertNullTypeUserDTO(TypeUserDTO actual) {
        assertNotNull(actual);
        assertNull(actual.getIdTypeUser());
        assertNull(actual.getDescription());
    }
}