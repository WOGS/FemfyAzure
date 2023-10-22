package com.femfy.femfyapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dto.UserDTO;

@SpringBootTest
@WebAppConfiguration
class UserControllerTest {

	private final static String BASE_URL="/api/v1/user";
	MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@BeforeEach
	void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	void saveUser() {
		try {
			UserDTO user = builduser();
			
			MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL+"/createUser")
					.accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(MapToJson(user))).andReturn();
			
			assertEquals(201, mockMvcResult.getResponse().getStatus());
			
			getUsers();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void updateUser() {
		try {
			UserDTO user = builduser();
			user.setIdUser(1L);
			user.setUserName("MariaArg");
			
			MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL+"/updateUser")
					.accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(MapToJson(user))).andReturn();
			
			assertEquals(200, mockMvcResult.getResponse().getStatus());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Test
	void getUsers() {
		try {
			MvcResult mockMvcResult = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL+"/getUsers")
					.accept(MediaType.APPLICATION_JSON)).andReturn();
			assertEquals(200, mockMvcResult.getResponse().getStatus());
			System.out.println("Usuarios = " + mockMvcResult.getResponse().getContentAsString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String MapToJson(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}
	
	private UserDTO builduser() {

		UserDTO userDTO = new UserDTO();

		userDTO.setBirthdate(new Date());
		userDTO.setFirstName("Maria");
		userDTO.setLastName("Argento");
		userDTO.setPhone("1122334455");
		userDTO.setEmail("femfy2023@gmail.com");
		userDTO.setIsSuscriptor(true);
		userDTO.setPassword("pass1234");
		userDTO.setUserName("MariaArgento2023");
		userDTO.setEmotion("Triste");
		userDTO.setLocalidad("La Matanza");

		return userDTO;
	}

}
