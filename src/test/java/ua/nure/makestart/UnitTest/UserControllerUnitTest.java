package ua.nure.makestart.UnitTest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import ua.nure.makestart.controller.UserController;
import ua.nure.makestart.dto.UserRegistrationDto;

import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerUnitTest {

    @Autowired
    private static MockMvc mockMvc;
    @Autowired
    private static ObjectMapper objectMapper;

    @BeforeAll
    public static void setup(WebApplicationContext webApplicationContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCreateUser() throws Exception {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setFirstName("Dmytro");
        userRegistrationDto.setLastName("Yenbaiev");
        userRegistrationDto.setAge(40);
        userRegistrationDto.setPassword("dmytro2003");
        userRegistrationDto.setUsername("kyrilka");
        userRegistrationDto.setEmail("exampple@icloud.com");

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRegistrationDto)))
                .andExpect(status().isCreated());

    }
}
