package ua.nure.makestart.service;

import org.springframework.stereotype.Service;
import ua.nure.makestart.dto.CvCreationDto;
import ua.nure.makestart.dto.UserInfoDto;
import ua.nure.makestart.dto.UserRegistrationDto;

@Service
public interface UserService {
    UserInfoDto getUserInfo(String username);

    void createUser(UserRegistrationDto userRegistrationDto);

    void createCv(CvCreationDto cvCreationDto);

}
