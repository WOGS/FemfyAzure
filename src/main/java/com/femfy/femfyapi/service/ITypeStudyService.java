package com.femfy.femfyapi.service;

import java.util.List;

import dto.TypeStudyDTO;

public interface ITypeStudyService {
	
    public TypeStudyDTO saveTypeStudy(TypeStudyDTO typeStudyDTO);

    public TypeStudyDTO updateTypeStudy(TypeStudyDTO typeStudyDTO);

    public String deleteTypeStudy(Long idTypeStudy);

    public TypeStudyDTO getTypeStudy(Long idTypeStudy);

    public List<TypeStudyDTO> getTypeStudies();
}
