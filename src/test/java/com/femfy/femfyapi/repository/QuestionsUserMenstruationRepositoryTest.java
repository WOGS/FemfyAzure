package com.femfy.femfyapi.repository;

import com.femfy.femfyapi.entity.QuestionsUserMenstruation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class QuestionsUserMenstruationRepositoryTest {

    @Autowired
    private QuestionsUserMenstruationRepository repository;

    @Test
    public void testFindById() {
        // Inserta
        QuestionsUserMenstruation entity = createEntity(30, false, 0, 3);
        repository.save(entity);

        // Busca
        Optional<QuestionsUserMenstruation> foundEntity = repository.findById(entity.getId());

        // Verifica
        assertThat(foundEntity).isPresent();
        assertThat(foundEntity.get()).isEqualTo(entity);
    }

    @Test
    public void testFindAll() {
        // Inserta
        QuestionsUserMenstruation entity1 = createEntity(30, false, 0, 3);
        QuestionsUserMenstruation entity2 = createEntity(35, true, 35, 4);
        repository.save(entity1);
        repository.save(entity2);

        // Obtiene
        List<QuestionsUserMenstruation> allEntities = repository.findAll();

        // Verifica
        assertThat(allEntities).hasSize(2);
    }

    @Test
    public void testSave() {
        // Crea
        QuestionsUserMenstruation entity = createEntity(30, true, 30, 3);
        QuestionsUserMenstruation savedEntity = repository.save(entity);

        // Verifica
        assertThat(savedEntity.getId()).isNotNull();
        assertThat(savedEntity.getLastCycleDuration()).isEqualTo(30);
    }

    @Test
    public void testDeleteById() {
        // Inserta
        QuestionsUserMenstruation entity = createEntity(30, true, 30, 3);
        repository.save(entity);

        // Elimina
        repository.deleteById(entity.getId());

        // Verifica
        Optional<QuestionsUserMenstruation> deletedEntity = repository.findById(entity.getId());
        assertThat(deletedEntity).isNotPresent();
    }

    private QuestionsUserMenstruation createEntity(Integer lastCycleDuration, Boolean regular, Integer regularCycleDuration, Integer bleedingDuration) {
        QuestionsUserMenstruation entity = new QuestionsUserMenstruation();
        entity.setLastCycleDuration(lastCycleDuration);
        entity.setRegular(regular);
        entity.setRegularCycleDuration(regularCycleDuration);
        entity.setBleedingDuration(bleedingDuration);
        return entity;
    }
}
