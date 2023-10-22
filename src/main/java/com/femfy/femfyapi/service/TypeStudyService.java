package com.femfy.femfyapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.femfy.femfyapi.entity.TypeStudy;
import com.femfy.femfyapi.repository.TypeStudyRepository;

import dto.TypeStudyDTO;

@Service
public class TypeStudyService implements ITypeStudyService {

    private final TypeStudyRepository typeStudyRepository;

    @Autowired
    public TypeStudyService(TypeStudyRepository typeStudyRepository) {
        this.typeStudyRepository = typeStudyRepository;
    }

    @Override
    public TypeStudyDTO saveTypeStudy(TypeStudyDTO typeStudyDTO) {
        TypeStudy typeStudy = mapToTypeStudy(typeStudyDTO);
        typeStudy = this.typeStudyRepository.save(typeStudy);
        return mapToTypeStudyDTO(typeStudy);
    }
    
    
    @Override
    public TypeStudyDTO updateTypeStudy(TypeStudyDTO typeStudyDTO){
        TypeStudy typeStudy = mapToTypeStudy(typeStudyDTO);
        typeStudy = this.typeStudyRepository.save(typeStudy);
        return mapToTypeStudyDTO(typeStudy);
    }

    @Override
    public String deleteTypeStudy(Long idTypeStudy) {
        try {
            this.typeStudyRepository.deleteById(idTypeStudy);
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
    public TypeStudyDTO getTypeStudy(Long idTypeStudy) {
        TypeStudy typeUser = this.typeStudyRepository.findById(idTypeStudy).orElseGet(TypeStudy::new);
        return mapToTypeStudyDTO(typeUser);
    }

    @Override
    public List<TypeStudyDTO> getTypeStudies() {
        List<TypeStudy> typeStuides = this.typeStudyRepository.findAll();
        return typeStuides.stream().map(this::mapToTypeStudyDTO).collect(Collectors.toList());
    }

    private TypeStudyDTO mapToTypeStudyDTO (TypeStudy typeStudy) {
    	TypeStudyDTO dto = new TypeStudyDTO();
        dto.setIdTypeStudy(typeStudy.getId());
        dto.setDescription(typeStudy.getDescription());
        return dto;
    }

    private TypeStudy mapToTypeStudy(TypeStudyDTO typeStudyDTO) {
    	TypeStudy typeStudy = new TypeStudy();
    	typeStudy.setId(typeStudyDTO.getIdTypeStudy());
    	typeStudy.setDescription(typeStudyDTO.getDescription());
        return typeStudy;
    }
}