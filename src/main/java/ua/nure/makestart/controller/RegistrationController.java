package ua.nure.makestart.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.nure.makestart.dto.LoginDto;
import ua.nure.makestart.dto.UserRegistrationDto;
import ua.nure.makestart.service.UserService;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Slf4j
@RestController
@Tag(name = "Authorization")
@RequiredArgsConstructor
@Validated
public class RegistrationController {

    @Autowired
    private final UserService userService;


    private AuthenticationManager authenticationManager;

    public void SignInController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
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
    public ResponseEntity<String> logIn(@RequestBody LoginDto loginDto, HttpServletRequest req) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()));
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(authentication);
        HttpSession session = req.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
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
