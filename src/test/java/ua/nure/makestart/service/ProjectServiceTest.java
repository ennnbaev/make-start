package ua.nure.makestart.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.nure.makestart.dao.ProjectRepo;
import ua.nure.makestart.mapper.ProjectMapper;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {

    @Mock
    private ProjectRepo projectRepo;

    @Mock
    private ProjectMapper projectMapper;

    @InjectMocks
    private ProjectService projectService;
}
