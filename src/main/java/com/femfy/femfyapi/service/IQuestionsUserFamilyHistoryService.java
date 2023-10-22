package com.femfy.femfyapi.service;

import dto.QuestionsUserFamilyHistoryDTO;

import java.util.List;
import java.util.Optional;

public interface IQuestionsUserFamilyHistoryService {

    List<QuestionsUserFamilyHistoryDTO> getQuestionsUserFamilyHistories();

    Optional<QuestionsUserFamilyHistoryDTO> getQuestionsUserFamilyHistory(Long id);

    QuestionsUserFamilyHistoryDTO updateQuestionsUserFamilyHistory(QuestionsUserFamilyHistoryDTO updatedDTO);

    QuestionsUserFamilyHistoryDTO saveQuestionsUserFamilyHistory(QuestionsUserFamilyHistoryDTO questionsUserFamilyHistoryDTO);

    void deleteQuestionsUserFamilyHistory(Long id);
}