package com.femfy.femfyapi.repository;

import com.femfy.femfyapi.entity.QuestionsUserFamilyHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class QuestionsUserFamilyHistoryRepositoryTest {

    @Autowired
    private QuestionsUserFamilyHistoryRepository questionsUserFamilyHistoryRepository;

    @BeforeEach
    public void setUp() {
        QuestionsUserFamilyHistory questionsUserFamilyHistory = new QuestionsUserFamilyHistory();
        questionsUserFamilyHistory.setBreastCancer(true);
        questionsUserFamilyHistory.setOvarianCancer(false);
        questionsUserFamilyHistory.setEndometriosis(true);
        questionsUserFamilyHistory.setUterineFibroids(false);
        questionsUserFamilyHistory.setSop(true);
        questionsUserFamilyHistory.setEarlyMenopause(false);

        questionsUserFamilyHistoryRepository.save(questionsUserFamilyHistory);
    }

    @Test
    public void testFindById() {
        // Inserta
        QuestionsUserFamilyHistory history = new QuestionsUserFamilyHistory();
        history.setBreastCancer(true);
        questionsUserFamilyHistoryRepository.save(history);

        // Busca
        Optional<QuestionsUserFamilyHistory> foundFamilyHistory = questionsUserFamilyHistoryRepository.findById(history.getId());

        // Verifica
        assertThat(foundFamilyHistory).isPresent();
        assertThat(foundFamilyHistory.get().getId()).isEqualTo(history.getId());
    }

    @Test
    public void testFindAll() {
        // Obtiene
        List<QuestionsUserFamilyHistory> pepino = questionsUserFamilyHistoryRepository.findAll();
        // Inserta
        QuestionsUserFamilyHistory history1 = new QuestionsUserFamilyHistory();
        history1.setBreastCancer(true);
        questionsUserFamilyHistoryRepository.save(history1);

        QuestionsUserFamilyHistory history2 = new QuestionsUserFamilyHistory();
        history2.setBreastCancer(false);
        questionsUserFamilyHistoryRepository.save(history2);

        // Obtiene
        List<QuestionsUserFamilyHistory> allFamilyHistories = questionsUserFamilyHistoryRepository.findAll();

        // Verifica
        assertThat(allFamilyHistories).hasSize(3);
    }

    @Test
    public void testSave() {
        // Crea
        QuestionsUserFamilyHistory history = new QuestionsUserFamilyHistory();
        history.setBreastCancer(true);
        QuestionsUserFamilyHistory savedQuestionsUserFamilyHistory = questionsUserFamilyHistoryRepository.save(history);

        // Verifica
        assertThat(savedQuestionsUserFamilyHistory.getId()).isNotNull();
        assertThat(savedQuestionsUserFamilyHistory.isBreastCancer()).isEqualTo(true);
    }

    @Test
    public void testDeleteById() {
        // Inserta
        QuestionsUserFamilyHistory questionsUserFamilyHistory = new QuestionsUserFamilyHistory();
        questionsUserFamilyHistory.setBreastCancer(true);
        questionsUserFamilyHistoryRepository.save(questionsUserFamilyHistory);

        // Elimina
        questionsUserFamilyHistoryRepository.deleteById(questionsUserFamilyHistory.getId());

        // Verifica
        Optional<QuestionsUserFamilyHistory> deletedFamilyHistory = questionsUserFamilyHistoryRepository.findById(questionsUserFamilyHistory.getId());
        assertThat(deletedFamilyHistory).isNotPresent();
    }
}
