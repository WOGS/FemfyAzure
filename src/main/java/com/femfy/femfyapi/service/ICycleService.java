package com.femfy.femfyapi.service;


import com.femfy.femfyapi.entity.Cycle;
import com.femfy.femfyapi.exception.CustomException;
import dto.CycleDTO;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ICycleService {

    public CycleDTO registerCycleStart(Cycle cycle) throws Exception;
    public CycleDTO registerCycleEnd(Cycle cycle) throws IOException, CustomException;
    public List<CycleDTO> getCycleHistory(Long idUser) throws CustomException;
    public CycleDTO getCycleByIdUserAndDateBeging(Long idUser, String dateBeging) throws IOException, CustomException;
    public Map<String, String> deleteCycle(Long id) throws CustomException;
    public CycleDTO updateCycle (Cycle cycle) throws CustomException;

}
