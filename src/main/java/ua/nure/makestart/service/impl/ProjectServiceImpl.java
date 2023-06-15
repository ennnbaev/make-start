package ua.nure.makestart.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.nure.makestart.dao.ProjectRepo;
import ua.nure.makestart.dto.CreationProjectDto;
import ua.nure.makestart.dto.DetailProjectDto;
import ua.nure.makestart.dto.ShortDetailProjectDto;
import ua.nure.makestart.mapper.ProjectMapper;
import ua.nure.makestart.model.Project;
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
    public void save(String username, CreationProjectDto creationProjectDto) {
        projectRepo.saveProjectByUsername(username, projectMapper.toModel(creationProjectDto));
    }

    @Override
    public List<ShortDetailProjectDto> findRandomNProjects(int size) {
        final var totalRecordsSize = projectRepo.count();
        final var bound = (int) (totalRecordsSize / size);
        int page = bound > 0 ? random.nextInt(bound) : 0;

        return projectMapper.toShortDetailDtoList(projectRepo.findAll(PageRequest.of(page, size)).getContent());
    }

    @Override
    public boolean isUserHasProjectByProjectNameAndUsername(String projectName, String username) {
        return projectRepo.existsByProjectNameAndOwner_Username(projectName, username);
    }

    @Override
    public void deleteProjectByProjectName(String projectName) {
        projectRepo.deleteByProjectName(projectName);
    }

    @Override
    public Project getProjectByName(String projectName) {
        return projectRepo.getProjectsByProjectName(projectName);
    }

    @Override
    public void updateProject(Project project) {
        projectRepo.save(project);
    }

    @Override
    public DetailProjectDto getProjectDetailsByProjectName(String projectName) {
        return projectMapper.toDetailDto(getProjectByName(projectName));
    }

    @Override
    public List<DetailProjectDto> getProjectDetailsByOwnerName(String username) {
        return projectMapper.toDetailDtoList(projectRepo.getProjectsByOwner_Username(username));
    }

    @Override
    public List<DetailProjectDto> getWorkProjectByTeammate(String username) {
        return projectMapper.toDetailDtoList(projectRepo.findByTeamUsersUsername(username));
    }
}
