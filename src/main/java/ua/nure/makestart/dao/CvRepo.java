package ua.nure.makestart.dao;

import org.springframework.data.repository.CrudRepository;
import ua.nure.makestart.model.Cv;

import java.util.UUID;

public interface CvRepo extends CrudRepository<Cv, UUID> {
}
