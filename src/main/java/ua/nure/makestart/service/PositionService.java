package ua.nure.makestart.service;

import org.springframework.stereotype.Service;
import ua.nure.makestart.dto.PositionDto;
import ua.nure.makestart.model.Project;

@Service
public interface PositionService {
    void addPositionToProject(Project project, PositionDto positionDto);

    void deletePositionById(String positionId);
}
