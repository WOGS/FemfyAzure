package dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class CycleDTO {

    Long id;
    @Schema(description = "ID del USUARIO", example = "2")
    Long idUser;

    @Schema(description = "Fecha inicio del ciclo", example = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date dateBeging;

    @Schema(description = "Fecha fin del ciclo", example = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date dateEnd;

    @Schema(description = "Estado de animo", example = "Enojada - Alegre - Pensativa - Triste")
    String status;

    @Schema(description = "Dias de sangrado", example = "Ej. 5")
    int daysOfBleeding;

}
