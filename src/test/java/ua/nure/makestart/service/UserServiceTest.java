package ua.nure.makestart.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.nure.makestart.dao.LanguageRepo;
import ua.nure.makestart.dao.SeniorityRepo;
import ua.nure.makestart.dao.UserRepo;
import ua.nure.makestart.dto.CvCreationDto;
import ua.nure.makestart.dto.LanguageDto;
import ua.nure.makestart.dto.UserInfoDto;
import ua.nure.makestart.dto.UserRegistrationDto;
import ua.nure.makestart.mapper.UserMapper;
import ua.nure.makestart.model.Cv;
import ua.nure.makestart.model.Languages;
import ua.nure.makestart.model.Seniority;
import ua.nure.makestart.model.Users;
import ua.nure.makestart.service.impl.UserServiceImpl;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private UserMapper userMapper;

    @Mock
    private SeniorityRepo seniorityRepo;

    @Mock
    private LanguageRepo languageRepo;

    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        userService = new UserServiceImpl(userRepo, userMapper, seniorityRepo, languageRepo);
    }

    @Test
    void testGetUserInfo() {
        // Подготовка данных и настройка поведения мок-объектов
        String username = "testUser";
        Users user = new Users();
        user.setUsername(username);
        UserInfoDto expectedUserInfoDto = new UserInfoDto();
        expectedUserInfoDto.setUsername(username);

        when(userRepo.findUsersByUsername(username)).thenReturn(user);
        when(userMapper.toUserInfoDto(user)).thenReturn(expectedUserInfoDto);

        // Вызов тестируемого метода
        UserInfoDto resultUserRegistrationDto = userService.getUserInfo(username);

        // Проверка результатов
        assertEquals(expectedUserInfoDto.getUsername(), resultUserRegistrationDto.getUsername());
    }

    @Test
    void testCreateUser() {
        // Подготовка данных и настройка поведения мок-объектов
        String username = "testUser";
        Users user = new Users();
        user.setUsername(username);
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setUsername(username);

        when(userMapper.toModel(userRegistrationDto)).thenReturn(user);

        // Вызов тестируемого метода
        userService.createUser(userRegistrationDto);

        // Проверка вызова методов и другие необходимые проверки
        verify(userRepo, times(1)).save(user);
        // Другие необходимые проверки
    }

    @Test
    void testCreateCv() {
        // Создание тестовых данных
        CvCreationDto cvCreationDto = new CvCreationDto();
        cvCreationDto.setUsername("username");
        cvCreationDto.setDescription("description");
        cvCreationDto.setSeniority("Trainee");
        LanguageDto languageDto = new LanguageDto();
        languageDto.setLanguageName("language1");
        cvCreationDto.setLanguages(Set.of(languageDto));
        cvCreationDto.setExperienceYears(5);
        Seniority seniority = new Seniority();
        seniority.setId("1");
        seniority.setName("Trainee");

        Users user = new Users();
        Cv cv = new Cv();
        user.setCv(cv);

        // Настройка поведения моков
        when(userRepo.findUsersByUsername("username")).thenReturn(user);
        when(seniorityRepo.findSeniorityByName("Trainee")).thenReturn(seniority);
        when(languageRepo.findLanguagesByLanguageName("language1")).thenReturn(new Languages());

        // Вызов метода, который вы хотите протестировать
        userService.createCv(cvCreationDto);

        // Проверка результатов
        verify(userRepo).save(user);
        assertEquals("description", cv.getDescription());
        assertEquals("Trainee", cv.getSeniority().getName());
        assertEquals(1, cv.getLanguages().size());
        assertEquals(5, cv.getExperienceYears());
    }
}