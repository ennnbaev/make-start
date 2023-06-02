package ua.nure.makestart.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.nure.makestart.dto.CreationProjectDto;
import ua.nure.makestart.dto.DetailProjectDto;
import ua.nure.makestart.dto.ShortDetailProjectDto;
import ua.nure.makestart.model.Project;

import java.util.List;

@Mapper(componentModel = "spring",uses = {TeamMapper.class, UserMapper.class, ProjectMapper.class, SeniorityMapper.class})
public interface ProjectMapper {
    CreationProjectDto toDto(Project project);

    @Mapping(target = "countOfTeammates", expression = "java(project.getTeam().getUsers().size())")
    @Mapping(target = "countOfProposition", expression = "java(project.getPositions() != null ? project.getPositions().getLanguages().size() : 0)")
    ShortDetailProjectDto toShortDetailDto(Project project);

    DetailProjectDto toDetailDto(Project project);

    Project toModel(CreationProjectDto creationProjectDto);

    List<CreationProjectDto> toDtoList(List<Project> project);

    List<DetailProjectDto> toDetailDtoList(List<Project> project);

    List<ShortDetailProjectDto> toShortDetailDtoList(List<Project> project);

}
