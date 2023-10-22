package com.femfy.femfyapi.service;

import dto.QuestionsUserMenstruationDTO;

import java.util.List;
import java.util.Optional;

public interface IQuestionsUserMenstruationService {

    List<QuestionsUserMenstruationDTO> getQuestionsUserMenstruations();

    Optional<QuestionsUserMenstruationDTO> getQuestionsUserMenstruation(Long id);

    QuestionsUserMenstruationDTO updateQuestionsUserMenstruation(QuestionsUserMenstruationDTO updatedDTO);
    QuestionsUserMenstruationDTO saveQuestionsUserMenstruation(QuestionsUserMenstruationDTO questionsUserMenstruationDTO);
    void deleteQuestionsUserMenstruation(Long id);
}