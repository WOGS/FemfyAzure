package com.femfy.femfyapi.service;

import dto.QuestionsUserMenstruationDTO;
import com.femfy.femfyapi.entity.QuestionsUserMenstruation;
import com.femfy.femfyapi.entity.User;
import com.femfy.femfyapi.repository.QuestionsUserMenstruationRepository;
import com.femfy.femfyapi.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionsUserMenstruationService implements IQuestionsUserMenstruationService {

    private final QuestionsUserMenstruationRepository questionsUserMenstruationRepository;

    @Autowired
    public QuestionsUserMenstruationService(QuestionsUserMenstruationRepository questionsUserMenstruationRepository) {
        this.questionsUserMenstruationRepository = questionsUserMenstruationRepository;
    }

    @Override
    public List<QuestionsUserMenstruationDTO> getQuestionsUserMenstruations() {
        List<QuestionsUserMenstruation> menstruationList = questionsUserMenstruationRepository.findAll();
        return menstruationList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<QuestionsUserMenstruationDTO> getQuestionsUserMenstruation(Long id) {
        Optional<QuestionsUserMenstruation> menstruation = questionsUserMenstruationRepository.findById(id);
        return menstruation.map(this::mapToDTO);
    }

    @Override
    public QuestionsUserMenstruationDTO saveQuestionsUserMenstruation(QuestionsUserMenstruationDTO questionsUserMenstruationDTO) {
        QuestionsUserMenstruation menstruation = mapToEntity(questionsUserMenstruationDTO);
        menstruation = questionsUserMenstruationRepository.save(menstruation);
        return mapToDTO(menstruation);
    }

    @Override
    public QuestionsUserMenstruationDTO updateQuestionsUserMenstruation(QuestionsUserMenstruationDTO updatedDTO) {
        Long idToUpdate = updatedDTO.getId();
        if (idToUpdate == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo para la actualización");
        }

        QuestionsUserMenstruation existingMenstruation = questionsUserMenstruationRepository.findById(idToUpdate)
                .orElseThrow(() -> new RuntimeException("No se encontró el objeto para actualizar"));

        copyProperties(updatedDTO, existingMenstruation);

        existingMenstruation = questionsUserMenstruationRepository.save(existingMenstruation);

        return mapToDTO(existingMenstruation);
    }

    private void copyProperties(QuestionsUserMenstruationDTO source, QuestionsUserMenstruation target) {
        if (source.getLastTime() != null) {
            target.setLastTime(source.getLastTime());
        }

        if (source.getLastCycleDuration() != null) {
            target.setLastCycleDuration(source.getLastCycleDuration());
        }

        if (source.getRegular() != null) {
            target.setRegular(source.getRegular());
        }

        if (source.getRegularCycleDuration() != null) {
            target.setRegularCycleDuration(source.getRegularCycleDuration());
        }

        if (source.getBleedingDuration() != null) {
            target.setBleedingDuration(source.getBleedingDuration());
        }
    }

    @Override
    public void deleteQuestionsUserMenstruation(Long id) {
        questionsUserMenstruationRepository.deleteById(id);
    }

    private QuestionsUserMenstruationDTO mapToDTO(QuestionsUserMenstruation menstruation) {
        if (menstruation == null) {
            throw new EntityNotFoundException("Menstruation not found");
        }

        QuestionsUserMenstruationDTO dto = new QuestionsUserMenstruationDTO();
        dto.setId(menstruation.getId());
        dto.setUserId(menstruation.getUser().getId());
        dto.setLastTime(menstruation.getLastTime());
        dto.setLastCycleDuration(menstruation.getLastCycleDuration());
        dto.setRegular(menstruation.getRegular());
        dto.setRegularCycleDuration(menstruation.getRegularCycleDuration());
        dto.setBleedingDuration(menstruation.getBleedingDuration());

        return dto;
    }

    private QuestionsUserMenstruation mapToEntity(QuestionsUserMenstruationDTO dto) {
        User user = new User();
        user.setId(dto.getUserId());

        QuestionsUserMenstruation menstruation = new QuestionsUserMenstruation();
        menstruation.setId(dto.getId());
        menstruation.setUser(user);
        menstruation.setLastTime(dto.getLastTime());
        menstruation.setLastCycleDuration(dto.getLastCycleDuration());
        menstruation.setRegular(dto.getRegular());
        menstruation.setRegularCycleDuration(dto.getRegularCycleDuration());
        menstruation.setBleedingDuration(dto.getBleedingDuration());
        return menstruation;
    }
}