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
import ua.nure.makestart.dto.CreationProjectDto;
import ua.nure.makestart.dto.DetailProjectDto;
import ua.nure.makestart.dto.ShortDetailProjectDto;
import ua.nure.makestart.service.ProjectService;

import java.util.List;

@CrossOrigin
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
                              @RequestBody CreationProjectDto creationProjectDto) {
        projectService.save(username, creationProjectDto);
    }

    @GetMapping
    @Operation(summary = "Find requested quantity of the projects as thumbnails and return them as a page result")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")})
    public List<ShortDetailProjectDto> findRandomNProjects(@Parameter @PositiveOrZero @RequestParam int size) {
        return projectService.findRandomNProjects(size);
    }

    @GetMapping("detail")
    @Operation(summary = "Get details about project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")})
    public DetailProjectDto getProjectDetails(@Parameter @RequestParam String projectName) {
        return projectService.getProjectDetailsByProjectName(projectName);
    }

    @GetMapping("detail/my")
    @Operation(summary = "Get details about my project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")})
    public List<DetailProjectDto> getMyProjectDetails(@Parameter @RequestParam String ownerName) {
        return projectService.getProjectDetailsByOwnerName(ownerName);
    }

    @GetMapping("detail/work")
    @Operation(summary = "Get details about projects in which I work")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")})
    public List<DetailProjectDto> getWorkProjectDetails(@Parameter @RequestParam String teammate) {
        return projectService.getWorkProjectByTeammate(teammate);
    }


    @DeleteMapping
    @Operation(summary = "Delete an existed project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")})
    @ResponseStatus(HttpStatus.OK)
    public void deleteProject(@Parameter @RequestParam String ownerUsername,
                              @Parameter @RequestParam String projectName) {
        if (!projectService.isUserHasProjectByProjectNameAndUsername(projectName, ownerUsername)) {
            throw new UnsupportedOperationException("You cannot delete not yours project");
        }
        projectService.deleteProjectByProjectName(projectName);
    }
}
