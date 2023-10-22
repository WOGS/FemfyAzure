package dto;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserDTO {
	@Schema(description = "ID del USUARIO", example = "2")
	Long idUser;
	@Schema(description = "ID tipo USUARIO", example = "2")
	Long typeUserID;
	@Schema(description = "Nombre del USUARIO", example = "Maria")
	String firstName;
	@Schema(description = "Apellido del USUARIO", example = "Argento")
	String lastName;
	@Schema(description = "Nick / User Name", example = "MariaArgento23")
	String userName;
	@Schema(description = "Contraseña del Usuario", example = "pass1234")
	String password;
	@Schema(description = "Usuario es suscriptor", example = "true")
	Boolean isSuscriptor;
	@Schema(description = "Fecha de Nacimiento del Usuario", example = "23/08/1986")
	Date birthdate;
	@Schema(description = "Telefono del Usuario", example = "1122334455")
	String phone;
	@Schema(description = "Email del Usuario", example = "femfy2023@gmail.com")
	String email;
	@Schema(description = "Descripcion de la emosión del Usuario", example = "Alegre/Triste")
	String emotion;
	@Schema(description = "Localidad del Usuario", example = "La Matanza")
	String localidad;
	@Schema(description = "Archivo de Avatar del Usuario, en formato String", example = "avatar")
	String avatar;
}
