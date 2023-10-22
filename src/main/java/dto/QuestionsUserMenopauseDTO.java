package dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class QuestionsUserMenopauseDTO {
    @Schema(description = "ID de las preguntas de la usuaria menopáusica", example = "1")
    private Long id;

    @Schema(description = "ID de la usuaria relacionada a las preguntas", example = "1")
    private Long userId;

    @Schema(description = "Sofocos", example = "true")
    private boolean suffocation;

    @Schema(description = "Cambios en el ciclo menstrual", example = "true")
    private boolean changesInMenstrualCycle;

    @Schema(description = "Sequedad vaginal", example = "true")
    private boolean vaginalDryness;

    @Schema(description = "Cambios en la piel y el cabello", example = "true")
    private boolean changesInSkinAndHair;

    @Schema(description = "Cambios en el estado de ánimo", example = "true")
    private boolean moodChanges;

    @Schema(description = "Dificultades para dormir", example = "true")
    private boolean sleepingDifficulties;

    @Schema(description = "Aumento de peso", example = "true")
    private boolean aumentoDePeso;

    @Schema(description = "Pérdida de densidad ósea", example = "true")
    private boolean lossOfBoneDensity;

    @Schema(description = "Cambios en la libido", example = "true")
    private boolean changesInLibido;
}