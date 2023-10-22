package com.femfy.femfyapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.femfy.femfyapi.service.UserService;

import dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	
	@Autowired
	private  UserService userService;
	
	@Operation(summary = "Realizar la consulta por un suario en particular")
	@ApiResponses(value ={// - 
		@ApiResponse(responseCode = "200", description = "Respuesta OK",
		content = {@Content (mediaType = "application/json")}),
		@ApiResponse(responseCode = "500", description = "Unexpected system error",
		content = {@Content (mediaType = "application/json")}),
		@ApiResponse(responseCode = "400", description = "Bad Request. Parametros invalidos",
		content = {@Content (mediaType = "application/json")}),
		@ApiResponse(responseCode = "404", description = "Usuario inexistente",
		content = {@Content (mediaType = "application/json")})

		})
	
	// ***** swagger - openapi *****
	@GetMapping("/{userId}") 
	public ResponseEntity<UserDTO> getUserById(@PathVariable("userId") Long userId) {
		UserDTO user = new UserDTO();
		user = userService.getUser(userId);
		if(!user.getEmail().isEmpty()){
			return new ResponseEntity<>(user, HttpStatus.OK);	
		}else {
			return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
			}		
	}
	
	@Operation(summary = "Realizar la consulta de todos los usuarios")
	@ApiResponses(value ={// - 
		@ApiResponse(responseCode = "200", description = "Respuesta OK",
		content = {@Content (mediaType = "application/json")}),
		@ApiResponse(responseCode = "500", description = "Unexpected system error",
		content = {@Content (mediaType = "application/json")}),
		@ApiResponse(responseCode = "400", description = "Bad Request. Parametros invalidos",
		content = {@Content (mediaType = "application/json")}),
		@ApiResponse(responseCode = "404", description = "Usuario inexistente",
		content = {@Content (mediaType = "application/json")})

		})
	
	// ***** swagger - openapi ****
	@GetMapping("/getUsers")
	public ResponseEntity<List<UserDTO>> getUsers(){
		List<UserDTO> users = new ArrayList<>();
		users = userService.getUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@Operation(summary = "Realizar alta de un usuario ")
	@ApiResponses(value ={// - 
		@ApiResponse(responseCode = "201", description = "Respuesta: Usuario Creado",
		content = {@Content (mediaType = "application/json")}),
		@ApiResponse(responseCode = "500", description = "Unexpected system error",
		content = {@Content (mediaType = "application/json")}),
		@ApiResponse(responseCode = "400", description = "Bad Request. Parametros invalidos",
		content = {@Content (mediaType = "application/json")}),
		@ApiResponse(responseCode = "404", description = "Usuario inexistente",
		content = {@Content (mediaType = "application/json")})

		})
	
	// ***** swagger - openapi ****
	
	@PostMapping("/createUser") 
	public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
		userDTO = userService.saveUser(userDTO);
		return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
	}
	
	
	@Operation(summary = "Actualizacion de los datos de un usuario ")
	@ApiResponses(value ={// - 
		@ApiResponse(responseCode = "200", description = "Respuesta OK",
		content = {@Content (mediaType = "application/json")}),
		@ApiResponse(responseCode = "500", description = "Unexpected system error",
		content = {@Content (mediaType = "application/json")}),
		@ApiResponse(responseCode = "400", description = "Bad Request. Parametros invalidos",
		content = {@Content (mediaType = "application/json")}),
		@ApiResponse(responseCode = "404", description = "Usuario inexistente",
		content = {@Content (mediaType = "application/json")})

		})
	
	// ***** swagger - openapi ****
	@PostMapping("/updateUser") 
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
		userDTO = userService.updateUser(userDTO);
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}

	@Operation(summary = "Eliminar un usuario mediante su Id")
	@ApiResponses(value ={// - 
		@ApiResponse(responseCode = "200", description = "Respuesta OK",
		content = {@Content (mediaType = "application/json")}),
		@ApiResponse(responseCode = "500", description = "Unexpected system error",
		content = {@Content (mediaType = "application/json")}),
		@ApiResponse(responseCode = "400", description = "Bad Request. Parametros invalidos",
		content = {@Content (mediaType = "application/json")}),
		@ApiResponse(responseCode = "404", description = "Usuario inexistente",
		content = {@Content (mediaType = "application/json")})

		})
	
	// ***** swagger - openapi ****
	@DeleteMapping("/{userId}") 
	public ResponseEntity<String> deleteUser(@PathVariable("userId") Long userId) {
		String responseDelete = "";
		
		responseDelete = userService.deleteUser(userId);
		if(responseDelete.equalsIgnoreCase("OK")){
			return new ResponseEntity<>("User delete OK", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("User don't exist", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
