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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetUserInfo() throws Exception {
        String username = "example_username";

        mockMvc.perform(get("/user/my-info/{username}", username))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Test
    public void testCreateCv() throws Exception {
        byte[] jsonData = Files.readAllBytes(Paths.get("cv_creation.json"));
        CvCreationDto cvCreationDto = objectMapper.readValue(jsonData, CvCreationDto.class);

        mockMvc.perform(post("/user/cv")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cvCreationDto)))
                .andExpect(status().isCreated())
                .andReturn();
    }


    @Test
    public void testCreateUser() throws Exception {
        byte[] jsonData = Files.readAllBytes(Paths.get("user_registration.json"));
        UserRegistrationDto userRegistrationDto = objectMapper.readValue(jsonData, UserRegistrationDto.class);

        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRegistrationDto)))
                .andExpect(status().isCreated())
                .andReturn();


    }
}


