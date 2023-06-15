package ua.nure.makestart.service;

import org.springframework.stereotype.Service;
import ua.nure.makestart.dto.CreationProjectDto;
import ua.nure.makestart.dto.DetailProjectDto;
import ua.nure.makestart.dto.ShortDetailProjectDto;
import ua.nure.makestart.model.Project;

import java.util.List;

@Service
public interface ProjectService {
    void save(String username, CreationProjectDto creationProjectDto);

    List<ShortDetailProjectDto> findRandomNProjects(int size);

    boolean isUserHasProjectByProjectNameAndUsername(String projectName, String username);

    void deleteProjectByProjectName(String projectName);

    Project getProjectByName(String projectName);

    void updateProject(Project project);

    DetailProjectDto getProjectDetailsByProjectName(String projectName);

    List<DetailProjectDto> getProjectDetailsByOwnerName(String username);

    List<DetailProjectDto> getWorkProjectByTeammate(String username);

}
