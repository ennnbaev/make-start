package ua.nure.makestart.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.nure.makestart.model.Project;
import ua.nure.makestart.model.Team;
import ua.nure.makestart.model.Users;
import ua.nure.makestart.service.ProjectService;
import ua.nure.makestart.service.TeamService;
import ua.nure.makestart.service.UserService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@RestController
@RequiredArgsConstructor
@Tag(name = "Team")
@RequestMapping("team")
@CrossOrigin
public class TeamController {

    private final ProjectService projectService;
    private final UserService userService;
    private final TeamService teamService;

    @PostMapping
    @Operation(summary = "Add a new teammate to project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    public void addTeammateProject(@Parameter @RequestParam String ownerUsername,
                                     @Parameter @RequestParam String projectName,
                                     @Parameter @RequestParam String teammateName,
                                     @Parameter @RequestParam String teamName) {
        Project project = projectService.getProjectByName(projectName);
        Users users = checkValidOperation(ownerUsername, projectName, teammateName);
        Team team = project.getTeam();
        if (team == null) {
            team = new Team();
            team.setTeamName(teamName);
            project.setTeam(team);
        }
        Set<Users> teamUsers = team.getUsers();
        if (teamUsers == null) {
            teamUsers = new HashSet<>();
            team.setUsers(teamUsers);
        }
        teamUsers.add(users);

        projectService.updateProject(project);
        teamService.createTeam(team);
    }

    @DeleteMapping
    @Operation(summary = "Delete a teammate from project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")})
    @ResponseStatus(HttpStatus.OK)
    public void deleteTeammate(@Parameter @RequestParam String ownerUsername,
                               @Parameter @RequestParam String projectName,
                               @Parameter @RequestParam String teammateName) {
        Project project = projectService.getProjectByName(projectName);
        Users users = checkValidOperation(ownerUsername, projectName, teammateName);

        if (project.getTeam() != null) {
            Team team = project.getTeam();
            team.getUsers().remove(users);
            teamService.createTeam(team);
        }
    }

    private Users checkValidOperation(String ownerUsername, String projectName, String teammateName) {
        Optional<Users> usersOptional = userService.getUserByUsername(teammateName);
        if (!projectService.isUserHasProjectByProjectNameAndUsername(projectName, ownerUsername)) {
            throw new UnsupportedOperationException("You cannot add a teammate for a project that does not belong to you");
        }
        return usersOptional.orElseThrow(() -> new UnsupportedOperationException("User not found"));
    }
}
