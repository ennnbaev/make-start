package ua.nure.makestart.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.nure.makestart.dto.ProjectDto;
import ua.nure.makestart.service.ProjectService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Project")
@RequestMapping("project")
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    @Operation(summary = "Create a new project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    public void createProject(@Parameter(name = "username", description = "Username of owner") @RequestParam String username,
                              @RequestBody ProjectDto projectDto) {
        projectService.save(username, projectDto);
    }

    @GetMapping
    @Operation(summary = "Find requested quantity of the projects as thumbnails and return them as a page result")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")})
    public List<ProjectDto> findRandomNProjects(@Parameter @PositiveOrZero @RequestParam int size) {
        return projectService.findRandomNProjects(size);
    }

}
