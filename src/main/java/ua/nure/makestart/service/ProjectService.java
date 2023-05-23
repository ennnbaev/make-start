package ua.nure.makestart.service;

import org.springframework.stereotype.Service;
import ua.nure.makestart.dto.ProjectDto;

import java.util.List;

@Service
public interface ProjectService {
    void save(String username, ProjectDto projectDto);

    List<ProjectDto> findRandomNProjects(int size);
}
