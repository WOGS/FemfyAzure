package com.femfy.femfyapi.repository;

import com.femfy.femfyapi.entity.QuestionsUserMenopause;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class QuestionsUserMenopauseRepositoryTest {

    @Autowired
    private QuestionsUserMenopauseRepository questionsUserMenopauseRepository;

    @BeforeEach
    public void setUp() {
        QuestionsUserMenopause questionsUserMenopause = new QuestionsUserMenopause();
        questionsUserMenopause.setSuffocation(true);
        questionsUserMenopause.setChangesInMenstrualCycle(false);
        questionsUserMenopause.setVaginalDryness(true);
        questionsUserMenopause.setChangesInSkinAndHair(false);
        questionsUserMenopause.setMoodChanges(true);
        questionsUserMenopause.setSleepingDifficulties(false);
        questionsUserMenopause.setAumentoDePeso(true);
        questionsUserMenopause.setLossOfBoneDensity(false);
        questionsUserMenopause.setChangesInLibido(true);

        questionsUserMenopauseRepository.save(questionsUserMenopause);
    }

    @Test
    public void testFindById() {
        // Inserta
        QuestionsUserMenopause questionsUserMenopause = new QuestionsUserMenopause();
        questionsUserMenopause.setSuffocation(true);
        questionsUserMenopauseRepository.save(questionsUserMenopause);

        // Busca
        Optional<QuestionsUserMenopause> foundMenopause = questionsUserMenopauseRepository.findById(questionsUserMenopause.getId());

        // Verifica
        assertThat(foundMenopause).isPresent();
        assertThat(foundMenopause.get().getId()).isEqualTo(questionsUserMenopause.getId());
    }

    @Test
    public void testFindAll() {
        // Obtiene
        List<QuestionsUserMenopause> menopauses = questionsUserMenopauseRepository.findAll();

        // Inserta
        QuestionsUserMenopause menopause1 = new QuestionsUserMenopause();
        menopause1.setSuffocation(true);
        questionsUserMenopauseRepository.save(menopause1);

        QuestionsUserMenopause menopause2 = new QuestionsUserMenopause();
        menopause2.setSuffocation(false);
        questionsUserMenopauseRepository.save(menopause2);

        // Obtiene
        List<QuestionsUserMenopause> allMenopauses = questionsUserMenopauseRepository.findAll();

        // Verifica
        assertThat(allMenopauses).hasSize(3);
    }

    @Test
    public void testSave() {
        // Crea
        QuestionsUserMenopause questionsUserMenopause = new QuestionsUserMenopause();
        questionsUserMenopause.setSuffocation(true);
        QuestionsUserMenopause savedMenopause = questionsUserMenopauseRepository.save(questionsUserMenopause);

        // Verifica
        assertThat(savedMenopause.getId()).isNotNull();
        assertThat(savedMenopause.getSuffocation()).isEqualTo(true);
    }

    @Test
    public void testDeleteById() {
        // Inserta
        QuestionsUserMenopause questionsUserMenopause = new QuestionsUserMenopause();
        questionsUserMenopause.setSuffocation(true);
        questionsUserMenopauseRepository.save(questionsUserMenopause);

        // Elimina
        questionsUserMenopauseRepository.deleteById(questionsUserMenopause.getId());

        // Verifica
        Optional<QuestionsUserMenopause> deletedMenopause = questionsUserMenopauseRepository.findById(questionsUserMenopause.getId());
        assertThat(deletedMenopause).isNotPresent();
    }
}