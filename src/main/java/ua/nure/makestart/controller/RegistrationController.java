package ua.nure.makestart.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ua.nure.makestart.dto.LoginDto;
import ua.nure.makestart.dto.UserRegistrationDto;
import ua.nure.makestart.service.UserService;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@Tag(name = "Auth")
@RequestMapping("auth")
public class RegistrationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    private final UserService userService;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .build();

    }

    @PostMapping
    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "409", description = "The user is with such email is already registered")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        userService.createUser(userRegistrationDto);
    }

    @PostMapping("/login")
    @Operation(summary = "log in into account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully sign-in"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "409", description = "You are already sign-in")
    })
    public ResponseEntity<String> logIn(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }

    @PostMapping("/logout")
    @Operation(summary = "log out into account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully log-out"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "409", description = "You are already sign-out")
    })
    public ResponseEntity<String> logoutUser() {
        SecurityContextHolder.clearContext();
        return new ResponseEntity<>("User logged out successfully!", HttpStatus.OK);
    }

}
