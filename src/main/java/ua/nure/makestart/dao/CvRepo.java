package ua.nure.makestart.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.nure.makestart.model.Cv;

import java.util.UUID;

@Repository
public interface CvRepo extends CrudRepository<Cv, UUID> {
}
