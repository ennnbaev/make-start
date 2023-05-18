package ua.nure.makestart.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.nure.makestart.dao.UserRepo;
import ua.nure.makestart.dto.UserDto;
import ua.nure.makestart.mapper.UserMapper;
import ua.nure.makestart.model.Users;
import ua.nure.makestart.service.impl.UserServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private UserMapper userMapper;

    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        userService = new UserServiceImpl(userRepo, userMapper);
    }

    @Test
    void testGetUserInfo() {
        // Подготовка данных и настройка поведения мок-объектов
        String username = "testUser";
        Users user = new Users();
        user.setUsername(username);
        UserDto expectedUserDto = new UserDto();
        expectedUserDto.setUsername(username);

        when(userRepo.findUsersByUsername(username)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(expectedUserDto);

        // Вызов тестируемого метода
        UserDto resultUserDto = userService.getUserInfo(username);

        // Проверка результатов
        assertEquals(expectedUserDto, resultUserDto);
        // Другие необходимые проверки
    }

    @Test
    void testCreateUser() {
        // Подготовка данных и настройка поведения мок-объектов
        String username = "testUser";
        Users user = new Users();
        user.setUsername(username);
        UserDto userDto = new UserDto();
        userDto.setUsername(username);

        when(userMapper.toModel(userDto)).thenReturn(user);

        // Вызов тестируемого метода
        userService.createUser(userDto);

        // Проверка вызова методов и другие необходимые проверки
        verify(userRepo, times(1)).save(user);
        // Другие необходимые проверки
    }
}