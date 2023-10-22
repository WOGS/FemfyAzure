package com.femfy.femfyapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.DataIntegrityViolationException;

import com.femfy.femfyapi.entity.TypeUser;
import com.femfy.femfyapi.repository.TypeUserRepository;

import dto.TypeUserDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeUserService implements ITypeUserService {

    private final TypeUserRepository typeUserRepository;

    @Autowired
    public TypeUserService(TypeUserRepository typeUserRepository) {
        this.typeUserRepository = typeUserRepository;
    }

    @Override
    public TypeUserDTO saveTypeUser(TypeUserDTO typeUserDTO) {
        TypeUser typeUser = mapToTypeUser(typeUserDTO);
        typeUser = this.typeUserRepository.save(typeUser);
        return mapToTypeUserDTO(typeUser);
    }

    @Override
    public TypeUserDTO updateTypeUser(TypeUserDTO typeUserDTO) {
        TypeUser typeUser = mapToTypeUser(typeUserDTO);
        typeUser = this.typeUserRepository.save(typeUser);
        return mapToTypeUserDTO(typeUser);
    }

    @Override
    public String deleteTypeUser(Long idTypeUser) {
        try {
            this.typeUserRepository.deleteById(idTypeUser);
            return "OK";
        } catch (EmptyResultDataAccessException e) {
            return "Error: No se encontró ningún registro con el ID proporcionado.";
        } catch (DataIntegrityViolationException e) {
            return "Error: No se puede eliminar este registro debido a restricciones de integridad de datos.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public TypeUserDTO getTypeUser(Long idTypeUser) {
        TypeUser typeUser = this.typeUserRepository.findById(idTypeUser).orElseGet(TypeUser::new);
        return mapToTypeUserDTO(typeUser);
    }

    @Override
    public List<TypeUserDTO> getTypeUsers() {
        List<TypeUser> typeUserListdb = this.typeUserRepository.findAll();
        return typeUserListdb.stream().map(this::mapToTypeUserDTO).collect(Collectors.toList());
    }

    private TypeUserDTO mapToTypeUserDTO(TypeUser typeUser) {
        TypeUserDTO dto = new TypeUserDTO();
        dto.setIdTypeUser(typeUser.getId());
        dto.setDescription(typeUser.getDescription());
        return dto;
    }

    private TypeUser mapToTypeUser(TypeUserDTO typeUserDTO) {
        TypeUser typeUser = new TypeUser();
        typeUser.setId(typeUserDTO.getIdTypeUser());
        typeUser.setDescription(typeUserDTO.getDescription());
        return typeUser;
    }
}