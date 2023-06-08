package ua.nure.makestart.IntegrationTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ua.nure.makestart.dto.CvCreationDto;
import ua.nure.makestart.dto.UserRegistrationDto;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateUser() throws Exception {
        byte[] jsonData = Files.readAllBytes(Paths.get("src/test/java/ua/nure/makestart/IntegrationTest/user_registration.json"));
        UserRegistrationDto userRegistrationDto = objectMapper.readValue(jsonData, UserRegistrationDto.class);

        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRegistrationDto)))
                .andExpect(status().isCreated());


    }
    @Test
    void testGetUserInfo() throws Exception {
        String username = "kyrilka";

        mockMvc.perform(get("/user/my-info/{username}", username))
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstName").value("Dmytro"))
                .andExpect(jsonPath("$.lastName").value("Yenbaiev"))
                .andExpect(jsonPath("$.age").value("40"))
                .andExpect(jsonPath("$.username").value("kyrilka"))
                .andExpect(jsonPath("$.email").value("exampple@icloud.com"));

    }
}


