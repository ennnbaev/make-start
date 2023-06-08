package ua.nure.makestart.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.nure.makestart.dto.CvCreationDto;
import ua.nure.makestart.dto.UserInfoDto;
import ua.nure.makestart.model.Project;
import ua.nure.makestart.model.Users;
import ua.nure.makestart.service.ProjectService;
import ua.nure.makestart.service.UserService;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@Tag(name = "User")
@RequestMapping("user")
public class UserController {

    private final UserService userService;
    private final ProjectService projectService;

    @GetMapping("my-info/{username}")
    @Operation(summary = "Find a user by username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")})
    @ResponseStatus(HttpStatus.OK)
    public UserInfoDto getUserInfo(@PathVariable String username) {
        return userService.getUserInfo(username);
    }

    @PostMapping("cv")
    @Operation(summary = "Create or update a Cv")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")})
    @ResponseStatus(HttpStatus.CREATED)
    public void createCv(@RequestBody CvCreationDto cvCreationDto) {

        userService.createCv(cvCreationDto);
    }

    @PostMapping("cv/send")
    @Operation(summary = "Send a Cv")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")})
    @ResponseStatus(HttpStatus.CREATED)
    public void sendSv(@Parameter @RequestParam String projectName,
                       @Parameter @RequestParam String username) {
        Users users = userService.getUserByUsername(username).orElseThrow();
        Project project = projectService.getProjectByName(projectName);
        project.getCv().add(users.getCv());
        projectService.updateProject(project);
    }

//    @PostMapping
//    @Operation(summary = "Create a new user")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "201", description = "CREATED"),
//            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
//            @ApiResponse(responseCode = "409", description = "The user is with such email is already registered")
//    })
//    @ResponseStatus(HttpStatus.CREATED)
//    public void createUser(@RequestBody UserRegistrationDto userRegistrationDto) {
//        userService.createUser(userRegistrationDto);
//    }

}
