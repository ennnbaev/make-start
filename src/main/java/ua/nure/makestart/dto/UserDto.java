package ua.nure.makestart.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.*;
import ua.nure.makestart.util.PatternHandler;

@Data
public class UserDto {
    @Schema(example = "Dmytro")
    @NotEmpty
    @Size(min = 2, max = 50)
    private String firstName;
    @Schema(example = "Yenbaiev")
    @NotEmpty
    @Size(min = 2, max = 50)
    private String lastName;
    @Schema(example = "40")
    @NotEmpty
    @Size(min = 18, max = 80)
    private int age;
    @Schema(example = "dmytro2003")
    @NotEmpty
    @Size(min = 8, max = 30)
    @Pattern(regexp = PatternHandler.PASSWORD)
    private String password;
    @Schema(example = "kyrilka")
    @NotEmpty
    @Size(min = 2, max = 50)
    @Pattern(regexp = PatternHandler.USERNAME)
    private String username;
    @Schema(example = "exampple@icloud.com")
    @NotEmpty
    @Pattern(regexp = PatternHandler.EMAIL)
    private String email;
}
