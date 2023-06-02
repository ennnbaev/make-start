package ua.nure.makestart.mapper;

import org.mapstruct.Mapper;
import ua.nure.makestart.dto.TeamDto;
import ua.nure.makestart.model.Team;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface TeamMapper {
    TeamDto toDto(Team team);
}
