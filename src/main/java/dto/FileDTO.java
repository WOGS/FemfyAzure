package dto;

import java.sql.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class FileDTO {
	
	@Schema(description = "ID del USUARIO", example = "2")
	Long idUser;
	
	@Schema(description = "Nombre del Documento", example = "archivos de prueba")
	String fileName;
	
	@Schema(description = "Archivo que se quiere trabajar, en formato base64", example = "UHJ1ZWJhIC0gSE9MQSBNVU5ETw0K")
	String fileBase64;
	
	@Schema(description = "Extension del archivos que se esta trabajando", example = "txt")
	String fileExt;
	
	@Schema(description = "ID del Archivo", example = "2")
	Long idFile;
	
	@Schema(description = "ID del tipo estudio", example = "2")
	TypeStudyDTO typeStudy;
	
	@Schema(description = "fecha del estudio", example = "2023-10-12")
	Date studyDate;
	
	@Schema(description = "descripcion del profesional", example = "Preguntarle al profesional, si los valores son aceptables")
	String description;
}
