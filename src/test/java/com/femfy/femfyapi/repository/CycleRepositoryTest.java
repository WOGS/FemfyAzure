package com.femfy.femfyapi.repository;

import com.femfy.femfyapi.Utils;
import com.femfy.femfyapi.entity.Cycle;
import com.femfy.femfyapi.exception.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class  CycleRepositoryTest {

    @Autowired
    CycleRepository cycleRepository;
    Cycle cycle;

    @BeforeEach
    void setUp() throws CustomException {
        cycle = new Cycle();
        cycle.setStatus("Alegre");
        cycle.setIdUser(1L);
        cycle.setId(1L);
        cycle.setDateEnd(Utils.parseDate("2023-10-06"));
        cycle.setDateBeging(Utils.parseDate("2023-10-06"));
    }

    @Test
    void findByIdUserAndDateBegingTest() throws CustomException {
        cycleRepository.save(cycle);
        Cycle resObtenida = cycleRepository.findByIdUserAndDateBeging(cycle.getIdUser(), cycle.getDateBeging());

        assertNotNull(resObtenida);
    }

    @Disabled
    @Test
    void findAllByIdUserTest() throws CustomException {
        Cycle cycle2 = new Cycle();
        cycle2.setId(2L);
        cycle2.setIdUser(1L);
        cycle2.setStatus("Triste");
        cycle2.setDateEnd(Utils.parseDate("2023-10-16"));
        cycle2.setDateBeging(Utils.parseDate("2023-10-16"));
        cycleRepository.save(cycle);
        cycleRepository.save(cycle2);

        List<Cycle> resObtenida = cycleRepository.findAllByIdUser(cycle.getIdUser());

        assertNotNull(resObtenida);
        assertEquals(2, resObtenida.size());

    }

    @Test
    void saveCycleTest(){
        Cycle resObtenida = cycleRepository.save(cycle);

        assertNotNull(resObtenida);
    }

    @Test
    void deleteCycleTest(){
        cycleRepository.save(cycle);
        cycleRepository.deleteById(cycle.getId());
        Optional<Cycle> resObtenida = cycleRepository.findById(cycle.getId());

        assertThat(resObtenida).isEmpty();
    }

    @Disabled
    @Test
    void updateCycleTest() throws CustomException {
        Cycle cycle2 = new Cycle();
        cycle2.setId(1L);
        cycle2.setIdUser(1L);
        cycle2.setStatus("Triste");
        cycle2.setDateEnd(Utils.parseDate("2023-10-16"));
        cycle2.setDateBeging(Utils.parseDate("2023-10-16"));
        cycleRepository.save(cycle2);
        Cycle cicloGuardado = cycleRepository.findById(cycle2.getId())
                .orElseThrow(() -> new CustomException("No se encontró el ciclo con ID " + cycle2.getId()));
        cicloGuardado.setStatus("Pensativa");
        Cycle cicloActualizado = cycleRepository.save(cicloGuardado);

        assertNotNull(cicloActualizado);
        assertThat(cicloActualizado.getStatus()).isEqualTo("Pensativa");

    }

}
