package ua.nure.makestart.service;

import org.springframework.stereotype.Service;
import ua.nure.makestart.dto.UserDto;

@Service
public interface UserService {
    UserDto getUserInfo(String username);

    void createUser(UserDto userDto);
}
