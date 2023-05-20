package ua.nure.makestart.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.nure.makestart.dao.LanguageRepo;
import ua.nure.makestart.dao.SeniorityRepo;
import ua.nure.makestart.dao.UserRepo;
import ua.nure.makestart.dto.CvCreationDto;
import ua.nure.makestart.dto.UserInfoDto;
import ua.nure.makestart.dto.UserRegistrationDto;
import ua.nure.makestart.mapper.UserMapper;
import ua.nure.makestart.model.Cv;
import ua.nure.makestart.model.Users;
import ua.nure.makestart.service.UserService;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final SeniorityRepo seniorityRepo;
    private final LanguageRepo languageRepo;

    @Override
    public UserInfoDto getUserInfo(String username) {
        return userMapper.toUserInfoDto(userRepo.findUsersByUsername(username));
    }

    @Override
    public void createUser(UserRegistrationDto userRegistrationDto) {
        userRepo.save(userMapper.toModel(userRegistrationDto));
    }

    @Override
    public void createCv(CvCreationDto cvCreationDto) {
        Users user = userRepo.findUsersByUsername(cvCreationDto.getUsername());
        Cv cv = user.getCv();
        if (cv == null) {
            cv = new Cv();
        }
        cv.setDescription(cvCreationDto.getDescription());
        cv.setSeniority(seniorityRepo.findSeniorityByName(cvCreationDto.getSeniority()));
        cv.setLanguages(cvCreationDto.getLanguages().stream().map(x -> languageRepo.findLanguagesByLanguageName(x.getLanguageName()))
                .collect(Collectors.toSet()));
        cv.setExperienceYears(cvCreationDto.getExperienceYears());
        user.setCv(cv);
        userRepo.save(user);
    }
}
