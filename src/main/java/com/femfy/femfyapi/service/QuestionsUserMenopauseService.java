package com.femfy.femfyapi.service;

import dto.QuestionsUserMenopauseDTO;
import com.femfy.femfyapi.entity.QuestionsUserMenopause;
import com.femfy.femfyapi.entity.User;
import com.femfy.femfyapi.repository.QuestionsUserMenopauseRepository;
import com.femfy.femfyapi.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionsUserMenopauseService implements IQuestionsUserMenopauseService {

    private final QuestionsUserMenopauseRepository questionsUserMenopauseRepository;

    @Autowired
    public QuestionsUserMenopauseService(QuestionsUserMenopauseRepository questionsUserMenopauseRepository) {
        this.questionsUserMenopauseRepository = questionsUserMenopauseRepository;
    }

    @Override
    public List<QuestionsUserMenopauseDTO> getQuestionsUserMenopause() {
        List<QuestionsUserMenopause> menopauseList = questionsUserMenopauseRepository.findAll();
        return menopauseList.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<QuestionsUserMenopauseDTO> getQuestionsUserMenopause(Long id) {
        Optional<QuestionsUserMenopause> menopause = questionsUserMenopauseRepository.findById(id);
        return menopause.map(this::mapToDTO);
    }

    @Override
    public QuestionsUserMenopauseDTO saveQuestionsUserMenopause(QuestionsUserMenopauseDTO questionsUserMenopauseDTO) {
        QuestionsUserMenopause menopause = mapToEntity(questionsUserMenopauseDTO);
        menopause = questionsUserMenopauseRepository.save(menopause);
        return mapToDTO(menopause);
    }

    @Override
    public QuestionsUserMenopauseDTO updateQuestionsUserMenopause(QuestionsUserMenopauseDTO updatedDTO) {
        Long idToUpdate = updatedDTO.getId();
        if (idToUpdate == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo para la actualización");
        }

        QuestionsUserMenopause existingMenopause = questionsUserMenopauseRepository.findById(idToUpdate)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el objeto para actualizar"));

        copyProperties(updatedDTO, existingMenopause);

        existingMenopause = questionsUserMenopauseRepository.save(existingMenopause);

        return mapToDTO(existingMenopause);
    }

    @Override
    public void deleteQuestionsUserMenopause(Long id) {
        questionsUserMenopauseRepository.deleteById(id);
    }

    private void copyProperties(QuestionsUserMenopauseDTO source, QuestionsUserMenopause target) {
        if (source.isSuffocation()) {
            target.setSuffocation(source.isSuffocation());
        }
        if (source.isChangesInMenstrualCycle()) {
            target.setChangesInMenstrualCycle(source.isChangesInMenstrualCycle());
        }
        if (source.isVaginalDryness()) {
            target.setVaginalDryness(source.isVaginalDryness());
        }
        if (source.isChangesInSkinAndHair()) {
            target.setChangesInSkinAndHair(source.isChangesInSkinAndHair());
        }
        if (source.isMoodChanges()) {
            target.setMoodChanges(source.isMoodChanges());
        }
        if (source.isSleepingDifficulties()) {
            target.setSleepingDifficulties(source.isSleepingDifficulties());
        }
        if(source.isAumentoDePeso()){
            target.setAumentoDePeso(source.isAumentoDePeso());
        }
        if (source.isLossOfBoneDensity()) {
            target.setLossOfBoneDensity(source.isLossOfBoneDensity());
        }
        if (source.isChangesInLibido()) {
            target.setChangesInLibido(source.isChangesInLibido());
        }

    }

    private QuestionsUserMenopauseDTO mapToDTO(QuestionsUserMenopause menopause) {
        if (menopause == null) {
            throw new EntityNotFoundException("Menopause not found");
        }

        QuestionsUserMenopauseDTO dto = new QuestionsUserMenopauseDTO();
        dto.setId(menopause.getId());
        dto.setUserId(menopause.getUser().getId());
        dto.setSuffocation(menopause.getSuffocation());
        dto.setChangesInMenstrualCycle(menopause.getChangesInMenstrualCycle());
        dto.setVaginalDryness(menopause.getVaginalDryness());
        dto.setChangesInSkinAndHair(menopause.getChangesInSkinAndHair());
        dto.setMoodChanges(menopause.getMoodChanges());
        dto.setSleepingDifficulties(menopause.getSleepingDifficulties());
        dto.setAumentoDePeso(menopause.getAumentoDePeso());
        dto.setLossOfBoneDensity(menopause.getLossOfBoneDensity());
        dto.setChangesInLibido(menopause.getChangesInLibido());

        return dto;
    }

    private QuestionsUserMenopause mapToEntity(QuestionsUserMenopauseDTO dto) {
        User user = new User();
        user.setId(dto.getUserId());

        QuestionsUserMenopause menopause = new QuestionsUserMenopause();
        menopause.setId(dto.getId());
        menopause.setUser(user);
        menopause.setSuffocation(dto.isSuffocation());
        menopause.setChangesInMenstrualCycle(dto.isChangesInMenstrualCycle());
        menopause.setVaginalDryness(dto.isVaginalDryness());
        menopause.setChangesInSkinAndHair(dto.isChangesInSkinAndHair());
        menopause.setMoodChanges(dto.isMoodChanges());
        menopause.setSleepingDifficulties(dto.isSleepingDifficulties());
        menopause.setAumentoDePeso(dto.isAumentoDePeso());
        menopause.setLossOfBoneDensity(dto.isLossOfBoneDensity());
        menopause.setChangesInLibido(dto.isChangesInLibido());
        return menopause;
    }
}
