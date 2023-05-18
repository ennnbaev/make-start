package ua.nure.makestart.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.nure.makestart.model.Project;

import java.util.UUID;

@Repository
public interface ProjectRepo extends CrudRepository<Project, UUID> {
}
