package ua.nure.makestart.mapper;

import org.mapstruct.Mapper;
import ua.nure.makestart.dto.ProjectDto;
import ua.nure.makestart.model.Project;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectDto toDto(Project project);

    Project toModel(ProjectDto projectDto);

    List<ProjectDto> toDtoList(List<Project> project);

}
