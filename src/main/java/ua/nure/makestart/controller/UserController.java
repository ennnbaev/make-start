package ua.nure.makestart.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.nure.makestart.dto.CvCreationDto;
import ua.nure.makestart.dto.UserInfoDto;
import ua.nure.makestart.service.UserService;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@Tag(name = "User")
@RequestMapping("user")
public class UserController {

    private final UserService userService;

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


}
