package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TypeUserDTO {
    @Schema(description = "ID del Tipo de Usuario", example = "1")
    Long idTypeUser;

    @Schema(description = "Descripción del Tipo de Usuario", example = "Menopausica")
    String description;
}
