package ua.nure.makestart.mapper;

import org.mapstruct.Mapper;
import ua.nure.makestart.dto.UserInfoDto;
import ua.nure.makestart.dto.UserRegistrationDto;
import ua.nure.makestart.model.Users;

@Mapper(componentModel = "spring", uses = CvMapper.class)
public interface UserMapper {
    UserRegistrationDto toRegistrationDto(Users users);

    Users toModel(UserRegistrationDto userRegistrationDto);

    UserInfoDto toUserInfoDto(Users users);
}
