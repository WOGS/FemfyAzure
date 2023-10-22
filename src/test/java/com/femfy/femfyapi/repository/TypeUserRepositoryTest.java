package com.femfy.femfyapi.repository;

import com.femfy.femfyapi.entity.TypeUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TypeUserRepositoryTest {

    @Autowired
    private TypeUserRepository typeUserRepository;

    @BeforeEach
    public void setUp() {
        createAndSaveTypeUser("Menopausica");
    }

    @Test
    public void testFindById() {
        // Inserta
        TypeUser typeUser = createAndSaveTypeUser("Menstruante");

        // Busca
        Optional<TypeUser> foundTypeUser = typeUserRepository.findById(typeUser.getId());

        // Verifica
        assertThat(foundTypeUser).isPresent();
        assertThat(foundTypeUser.get().getId()).isEqualTo(typeUser.getId());
    }

    @Test
    public void testFindAllTypeUsers() {
        // Inserta
        createAndSaveTypeUser("Menstruante");
        createAndSaveTypeUser("No menstrua - hormonal");

        // Obtiene
        List<TypeUser> allTypeUsers = typeUserRepository.findAll();

        // Verifica
        assertThat(allTypeUsers).hasSize(3); // Incluyendo el que se creó en setUp()
    }

    @Test
    public void testSaveTypeUser() {
        // Crea
        TypeUser typeUser = createAndSaveTypeUser("Menstruante");

        // Verifica
        assertThat(typeUser.getId()).isNotNull();
        assertThat(typeUser.getDescription()).isEqualTo("Menstruante");
    }

    @Test
    public void testDeleteById() {
        // Inserta
        TypeUser typeUser = createAndSaveTypeUser("Menstruante");

        // Elimina
        typeUserRepository.deleteById(typeUser.getId());

        // Verifica
        Optional<TypeUser> deletedTypeUser = typeUserRepository.findById(typeUser.getId());
        assertThat(deletedTypeUser).isNotPresent();
    }

    private TypeUser createAndSaveTypeUser(String description) {
        TypeUser typeUser = new TypeUser();
        typeUser.setDescription(description);
        return typeUserRepository.save(typeUser);
    }
}