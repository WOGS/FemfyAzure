package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TypeStudyDTO {
    @Schema(description = "ID del Tipo estudio", example = "1")
    Long idTypeStudy;

    @Schema(description = "Descripción del estudio", example = "Mamografia")
    String description;
}
