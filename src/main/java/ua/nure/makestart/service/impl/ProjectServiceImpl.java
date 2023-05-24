package ua.nure.makestart.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.nure.makestart.dao.ProjectRepo;
import ua.nure.makestart.dto.ProjectDto;
import ua.nure.makestart.mapper.ProjectMapper;
import ua.nure.makestart.service.ProjectService;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepo projectRepo;
    private final ProjectMapper projectMapper;
    private final Random random = new Random();


    @Override
    public void save(String username, ProjectDto projectDto) {
        projectRepo.saveProjectByUsername(username, projectMapper.toModel(projectDto));
    }

    @Override
    public List<ProjectDto> findRandomNProjects(int size) {
        final var totalRecordsSize = projectRepo.count();
        final var bound = (int) (totalRecordsSize / size);
        int page = bound > 0 ? random.nextInt(bound) : 0;

        return projectMapper.toDtoList(projectRepo.findAll(PageRequest.of(page, size)).getContent());
    }
}
