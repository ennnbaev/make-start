package ua.nure.makestart.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.nure.makestart.dao.TeamRepo;
import ua.nure.makestart.model.Team;
import ua.nure.makestart.service.TeamService;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepo teamRepo;

    @Override
    public void createTeam(Team team) {
        teamRepo.save(team);
    }
}
