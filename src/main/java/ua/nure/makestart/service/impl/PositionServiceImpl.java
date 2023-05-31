package ua.nure.makestart.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.nure.makestart.dao.LanguageRepo;
import ua.nure.makestart.dao.PositionRepo;
import ua.nure.makestart.dao.SeniorityRepo;
import ua.nure.makestart.dto.PositionDto;
import ua.nure.makestart.model.Position;
import ua.nure.makestart.model.Project;
import ua.nure.makestart.service.PositionService;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    private final PositionRepo positionRepo;
    private final SeniorityRepo seniorityRepo;
    private final LanguageRepo languageRepo;

    @Override
    public void addPositionToProject(Project project, PositionDto positionDto) {
        Position position = new Position();
        position.setExperienceYears(positionDto.getExperienceYears());
        position.setSeniority(seniorityRepo.findSeniorityByName(positionDto.getSeniority()));
        position.setLanguages(positionDto.getLanguages().stream().map(x -> languageRepo.findLanguagesByLanguageName(x.getLanguageName()))
                .collect(Collectors.toSet()));
        position.setProject(project);
        positionRepo.save(position);
    }

    @Override
    public void deletePositionById(String positionId) {
        positionRepo.deletePositionById(positionId);
    }
}
