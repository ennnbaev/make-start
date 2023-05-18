package ua.nure.makestart.mapper;

import org.mapstruct.Mapper;
import ua.nure.makestart.dto.UserDto;
import ua.nure.makestart.model.Users;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(Users users);

    Users toModel(UserDto userDto);
}
