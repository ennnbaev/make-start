package ua.nure.makestart.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreationProjectDto {
    @Schema(example = "Project")
    @NotEmpty
    @Size(min = 1, max = 20)
    private String projectName;
    @Schema(example = "The main goal of the project...")
    @NotEmpty
    @Size(min = 2, max = 255)
    private String description;
    @Schema(example = "3500")
    @NotEmpty
    @PositiveOrZero
    private double price;

}
