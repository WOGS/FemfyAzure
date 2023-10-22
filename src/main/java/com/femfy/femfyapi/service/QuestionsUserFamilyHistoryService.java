package com.femfy.femfyapi.service;

import dto.QuestionsUserFamilyHistoryDTO;
import com.femfy.femfyapi.entity.QuestionsUserFamilyHistory;
import com.femfy.femfyapi.entity.User;
import com.femfy.femfyapi.repository.QuestionsUserFamilyHistoryRepository;
import com.femfy.femfyapi.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionsUserFamilyHistoryService implements IQuestionsUserFamilyHistoryService {

    private final QuestionsUserFamilyHistoryRepository questionsUserFamilyHistoryRepository;

    @Autowired
    public QuestionsUserFamilyHistoryService(QuestionsUserFamilyHistoryRepository questionsUserFamilyHistoryRepository) {
        this.questionsUserFamilyHistoryRepository = questionsUserFamilyHistoryRepository;
    }

    @Override
    public List<QuestionsUserFamilyHistoryDTO> getQuestionsUserFamilyHistories() {
        List<QuestionsUserFamilyHistory> familyHistoryList = questionsUserFamilyHistoryRepository.findAll();
        return familyHistoryList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<QuestionsUserFamilyHistoryDTO> getQuestionsUserFamilyHistory(Long id) {
        Optional<QuestionsUserFamilyHistory> familyHistory = questionsUserFamilyHistoryRepository.findById(id);
        return familyHistory.map(this::mapToDTO);
    }

    @Override
    public QuestionsUserFamilyHistoryDTO saveQuestionsUserFamilyHistory(QuestionsUserFamilyHistoryDTO questionsUserFamilyHistoryDTO) {
        QuestionsUserFamilyHistory familyHistory = mapToEntity(questionsUserFamilyHistoryDTO);
        familyHistory = questionsUserFamilyHistoryRepository.save(familyHistory);
        return mapToDTO(familyHistory);
    }

    @Override
    public QuestionsUserFamilyHistoryDTO updateQuestionsUserFamilyHistory(QuestionsUserFamilyHistoryDTO updatedDTO) {
        Long idToUpdate = updatedDTO.getId();
        if (idToUpdate == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo para la actualización");
        }

        QuestionsUserFamilyHistory existingFamilyHistory = questionsUserFamilyHistoryRepository.findById(idToUpdate)
                .orElseThrow(() -> new RuntimeException("No se encontró el objeto para actualizar"));

        copyProperties(updatedDTO, existingFamilyHistory);

        existingFamilyHistory = questionsUserFamilyHistoryRepository.save(existingFamilyHistory);

        return mapToDTO(existingFamilyHistory);
    }

    private void copyProperties(QuestionsUserFamilyHistoryDTO source, QuestionsUserFamilyHistory target) {
        if(source.isBreastCancer()){
            target.setBreastCancer(source.isBreastCancer());
        }
        if(source.isOvarianCancer()){
            target.setOvarianCancer(source.isOvarianCancer());
        }
        if(source.isEndometriosis()){
            target.setEndometriosis(source.isEndometriosis());
        }
        if(source.isUterineFibroids()){
            target.setUterineFibroids(source.isUterineFibroids());
        }
        if(source.isSop()){
            target.setSop(source.isSop());
        }
        if(source.isEarlyMenopause()){
            target.setEarlyMenopause(source.isEarlyMenopause());
        }
    }

    @Override
    public void deleteQuestionsUserFamilyHistory(Long id) {
        questionsUserFamilyHistoryRepository.deleteById(id);
    }

    private QuestionsUserFamilyHistoryDTO mapToDTO(QuestionsUserFamilyHistory familyHistory) {
        if (familyHistory == null) {
            throw new EntityNotFoundException("Family history not found");
        }

        QuestionsUserFamilyHistoryDTO dto = new QuestionsUserFamilyHistoryDTO();
        dto.setId(familyHistory.getId());
        dto.setUserId(familyHistory.getUser().getId());
        dto.setBreastCancer(familyHistory.isBreastCancer());
        dto.setOvarianCancer(familyHistory.isOvarianCancer());
        dto.setEndometriosis(familyHistory.isEndometriosis());
        dto.setUterineFibroids(familyHistory.isUterineFibroids());
        dto.setSop(familyHistory.isSop());
        dto.setEarlyMenopause(familyHistory.isEarlyMenopause());

        return dto;
    }

    private QuestionsUserFamilyHistory mapToEntity(QuestionsUserFamilyHistoryDTO dto) {
        User user = new User();
        user.setId(dto.getUserId());

        QuestionsUserFamilyHistory familyHistory = new QuestionsUserFamilyHistory();
        familyHistory.setId(dto.getId());
        familyHistory.setUser(user);
        familyHistory.setBreastCancer(dto.isBreastCancer());
        familyHistory.setOvarianCancer(dto.isOvarianCancer());
        familyHistory.setEndometriosis(dto.isEndometriosis());
        familyHistory.setUterineFibroids(dto.isUterineFibroids());
        familyHistory.setSop(dto.isSop());
        familyHistory.setEarlyMenopause(dto.isEarlyMenopause());
        return familyHistory;
    }
}