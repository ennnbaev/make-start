package ua.nure.makestart.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.nure.makestart.dao.UserRepo;
import ua.nure.makestart.dto.UserDto;
import ua.nure.makestart.mapper.UserMapper;
import ua.nure.makestart.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;

    @Override
    public UserDto getUserInfo(String username) {
        return userMapper.toDto(userRepo.findUsersByUsername(username));
    }

    @Override
    public void createUser(UserDto userDto) {
        userRepo.save(userMapper.toModel(userDto));
    }
}
