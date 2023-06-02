package ua.nure.makestart.UnitTest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.nure.makestart.controller.RegistrationController;
import ua.nure.makestart.dto.LoginDto;
import ua.nure.makestart.dto.UserRegistrationDto;
import ua.nure.makestart.service.UserService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RegistrationControllerUnitTest {

    @Autowired
    private static MockMvc mockMvc;

    @Autowired
    private static UserService userService;

    @BeforeAll
    public static void setup(WebApplicationContext webApplicationContext) {
        userService = webApplicationContext.getBean(UserService.class);
        RegistrationController registrationController = new RegistrationController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(registrationController).build();
    }

    @Test
    public void testLogoutUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/logout")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}