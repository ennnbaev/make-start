package ua.nure.makestart.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.nure.makestart.dto.PositionDto;
import ua.nure.makestart.service.PositionService;
import ua.nure.makestart.service.ProjectService;

@RestController
@RequiredArgsConstructor
@Tag(name = "Position")
@RequestMapping("position")
public class PositionController {
    private final ProjectService projectService;
    private final PositionService positionService;

    @PostMapping
    @Operation(summary = "Add a new position to project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    public void addPositionToProject(@Parameter @RequestParam String ownerUsername,
                                     @Parameter @RequestParam String projectName,
                                     @RequestBody PositionDto positionDto) {
        if (!projectService.isUserHasProjectByProjectNameAndUsername(projectName, ownerUsername)) {
            throw new UnsupportedOperationException("You cannot add position for not yours project");
        }
        positionService.addPositionToProject(projectService.getProjectByName(projectName), positionDto);
    }

    @DeleteMapping
    @Operation(summary = "Delete position from project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")})
    @ResponseStatus(HttpStatus.OK)
    public void deletePositionFromProject(@Parameter @RequestParam String ownerUsername,
                                          @Parameter @RequestParam String projectName,
                                          @Parameter @RequestParam String positionId) {
        if (!projectService.isUserHasProjectByProjectNameAndUsername(projectName, ownerUsername)) {
            throw new UnsupportedOperationException("You cannot delete not yours project");
        }
        positionService.deletePositionById(positionId);
    }
}
