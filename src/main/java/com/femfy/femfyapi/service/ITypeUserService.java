package com.femfy.femfyapi.service;

import java.util.List;

import dto.TypeUserDTO;

public interface ITypeUserService {
    public TypeUserDTO saveTypeUser(TypeUserDTO typeUserDTO);

    public TypeUserDTO updateTypeUser(TypeUserDTO typeUserDTO);

    public String deleteTypeUser(Long idTypeUser);

    public TypeUserDTO getTypeUser(Long idTypeUser);

    public List<TypeUserDTO> getTypeUsers();
}
