package ua.nure.makestart.dao;

import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.nure.makestart.model.Seniority;

@Repository
public interface SeniorityRepo extends CrudRepository<Seniority, String> {
    @Transactional
    Seniority findSeniorityByName(String name);
}
