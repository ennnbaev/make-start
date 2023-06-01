package ua.nure.makestart.service;

import org.springframework.stereotype.Service;
import ua.nure.makestart.dto.ProjectDto;
import ua.nure.makestart.model.Project;

import java.util.List;

@Service
public interface ProjectService {
    void save(String username, ProjectDto projectDto);

    List<ProjectDto> findRandomNProjects(int size);

    boolean isUserHasProjectByProjectNameAndUsername(String projectName, String username);

    void deleteProjectByProjectName(String projectName);

    Project getProjectByName(String projectName);

    void updateProject(Project project);

}
