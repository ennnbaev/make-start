package ua.nure.makestart.service;

import org.springframework.stereotype.Service;
import ua.nure.makestart.dto.CvCreationDto;
import ua.nure.makestart.dto.UserInfoDto;
import ua.nure.makestart.dto.UserRegistrationDto;
import ua.nure.makestart.model.Users;

import java.util.Optional;

@Service
public interface UserService {
    UserInfoDto getUserInfo(String username);

    void createUser(UserRegistrationDto userRegistrationDto);

    void createCv(CvCreationDto cvCreationDto);

    Optional<Users> getUserByUsername(String username);
}
