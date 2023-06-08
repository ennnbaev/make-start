package ua.nure.makestart.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.nure.makestart.dao.UserRepo;
import ua.nure.makestart.dto.LoginDto;
import ua.nure.makestart.dto.UserRegistrationDto;
import ua.nure.makestart.model.Users;
import ua.nure.makestart.service.UserService;

import java.util.Optional;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@CrossOrigin
@Slf4j
@RestController
@Tag(name = "Authorization")
@RequiredArgsConstructor
@Validated
public class RegistrationController {

    private final UserRepo userRepo;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    @Operation(summary = "log in into account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully sign-in"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "409", description = "You are already sign-in")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> logIn(@RequestBody LoginDto loginDto) {
        Optional<Users> users = userRepo.findUsersByUsername(loginDto.getUsername());
        Users user = users.orElse(null);
        if (user == null) {
            return new ResponseEntity<>("User don't exist!.", HttpStatus.NOT_FOUND);
        } else {
            if (!user.getPassword().equals(loginDto.getPassword())) {
                return new ResponseEntity<>("Invalid password", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
        }
    }

    @PostMapping("/user")
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

    @PostMapping("/false/login")
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
}
