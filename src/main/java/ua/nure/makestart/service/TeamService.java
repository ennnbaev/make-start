package ua.nure.makestart.service;

import org.springframework.stereotype.Service;
import ua.nure.makestart.model.Team;

@Service
public interface TeamService {

    void createTeam(Team team);
}
