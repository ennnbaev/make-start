package ua.nure.makestart.dao;

import org.springframework.data.repository.CrudRepository;
import ua.nure.makestart.model.Project;

import java.util.UUID;

public interface ProjectRepo extends CrudRepository<Project, UUID> {
}
