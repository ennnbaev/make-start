package ua.nure.makestart.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class PositionDto {
    @Schema(example = "Trainee/Junior/Middle/Seniority")
    @NotEmpty
    @Size(min = 2, max = 10)
    private String seniority;
    @NotEmpty
    private String positionStatus;
    @Schema(example = "2")
    @NotEmpty
    @PositiveOrZero
    private int experienceYears;
    @NotEmpty
    private Set<LanguageDto> languages;
}
