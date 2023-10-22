package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class QuestionsUserFamilyHistoryDTO {
    @Schema(description = "ID de la historia familiar del usuario", example = "1")
    private Long id;

    @Schema(description = "ID del usuario relacionado", example = "1")
    private Long userId;

    @Schema(description = "Historial de cáncer de mama", example = "true")
    private boolean breastCancer;

    @Schema(description = "Historial de cáncer de ovario", example = "true")
    private boolean ovarianCancer;

    @Schema(description = "Historial de endometriosis", example = "true")
    private boolean endometriosis;

    @Schema(description = "Historial de fibromas uterinos", example = "true")
    private boolean uterineFibroids;

    @Schema(description = "Historial de síndrome de ovario poliquístico (SOP)", example = "true")
    private boolean sop;

    @Schema(description = "Historial de menopausia temprana", example = "true")
    private boolean earlyMenopause;
}