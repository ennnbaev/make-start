package ua.nure.makestart.dao;

import org.springframework.data.repository.CrudRepository;
import ua.nure.makestart.model.Team;


public interface TeamRepo extends CrudRepository<Team, String> {
}
