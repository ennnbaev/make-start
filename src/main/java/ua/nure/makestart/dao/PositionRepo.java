package ua.nure.makestart.dao;

import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.nure.makestart.model.Position;

@Repository
@Transactional
public interface PositionRepo extends CrudRepository<Position, String> {

    void deletePositionById(String id);
}
