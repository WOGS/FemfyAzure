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

import com.femfy.femfyapi.service.IFileService;
import com.femfy.femfyapi.service.IUploadFileService;

import dto.FileDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/file", produces = MediaType.APPLICATION_JSON_VALUE)
public class FileController {
	@Autowired
	IUploadFileService iUploadFileService;
	
	@Autowired
	IFileService iFileService;
	
	@Operation(summary = "Permite subir un docuemento dentro del repositorio de AZURE. todos los datos son requeridos")
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
	@PostMapping("/uploadFile")
	public ResponseEntity<FileDTO> uploadFile(@RequestBody FileDTO fileDTO)
	{
		fileDTO	= iFileService.insertFile(fileDTO);
		
		try {
			if(fileDTO.getIdFile()!= null) {
				fileDTO.setFileName(NameOfDocuement(fileDTO));
				iUploadFileService.uploadFile(fileDTO);
				
					return new ResponseEntity<>(fileDTO, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(fileDTO,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(fileDTO,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	

	@Operation(summary = "Permite descargar un docuemento del repositorio de AZURE. se requiere IdUsuario + IdFile + nombreDelFile DESCARGAR")
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
	@PostMapping("/downloadFile")
	public ResponseEntity<FileDTO> downloadFile(@RequestBody FileDTO fileDTO)
	{
		fileDTO.setFileName(NameOfDocuement(fileDTO));
		fileDTO =  iUploadFileService.downloadFile(fileDTO);
		return new ResponseEntity<>(fileDTO, HttpStatus.OK);
	}
	
	@Operation(summary = "Permite eliminar un docuemento del repositorio de AZURE. Solo se requiere id del File para poder ELIMINAR el registro de la base + AZURE")
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
	
	@DeleteMapping("/deleteFile")
	public ResponseEntity<String> deleteFile(@RequestBody FileDTO fileDTO)
	{
		fileDTO = iFileService.getFileById(fileDTO.getIdFile());
		
		if(!fileDTO.getFileName().isEmpty() && fileDTO.getFileName()!=null) {
			fileDTO.setFileName(NameOfDocuement(fileDTO));
			String deleteResult = iUploadFileService.deleteFile(fileDTO);
			
			if(deleteResult.equalsIgnoreCase("OK")) {
				String result = iFileService.deleteFile(fileDTO);
				
				if(result.equalsIgnoreCase("OK")){
					return new ResponseEntity<String>("Documento eliminado",HttpStatus.OK);
				}else {
					return new ResponseEntity<String>("No se pudo eliminar el Documento",HttpStatus.INTERNAL_SERVER_ERROR);
				}
				
			}else {
				return new ResponseEntity<String>("No se pudo eliminar el Documento",HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}else {
			return new ResponseEntity<String>("No existe el ID del docuemnto enviado",HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@Operation(summary = "Realizar la consulta de todos los documentos de un usuario. Solo es necesaro enviar el ID del usuario, para recuperar todos los documentos")
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
	public ResponseEntity<List<FileDTO>> getUserById(@PathVariable("userId") Long userId) {
		List<FileDTO> documents = new ArrayList<>();
		documents = iFileService.findDocumentsByIdUser(userId);
		if(documents.size() >0){
			return new ResponseEntity<>(documents, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(documents, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	public String NameOfDocuement(FileDTO fileDTO) {
		return fileDTO.getFileName()+"_idUser_"+fileDTO.getIdUser()+"_idFile_"+fileDTO.getIdFile();
	}
	
}
