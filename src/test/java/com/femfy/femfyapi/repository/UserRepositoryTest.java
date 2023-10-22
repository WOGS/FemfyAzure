package com.femfy.femfyapi.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.femfy.femfyapi.entity.User;

@DataJpaTest
class UserRepositoryTest {

	@Autowired
	UserRepository repository;
	@Autowired
	TestEntityManager entityManager;
	
	@BeforeEach
	public void setUp() throws Exception {
		User user= new User();
		user.setEmail("peper2r@gmail.com");
		user.setFirstName("pepe");
		user.setLastName("lopez");
		user.setPhone("123456789");
		user.setIsSuscriptor(true);
		user.setEmotion("Cansada");
		user.setLocalidad("La Matanza");
		
		entityManager.persist(user);
	}

	@Test
	public void findById() {
		Optional<User> user = repository.findById(1L);
		assertEquals(user.get().getFirstName(), "pepe");
		System.out.println("user.get() =" + user.get());
	}
	
	@Test
	public void findUsers() {
	    List<User> users = repository.findAll();

	    assertThat(users).hasSize(1);
	}

}
