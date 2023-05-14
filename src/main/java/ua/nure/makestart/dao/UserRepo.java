package ua.nure.makestart.dao;

import org.springframework.data.repository.CrudRepository;
import ua.nure.makestart.model.Users;

import java.util.UUID;

public interface UserRepo extends CrudRepository<Users, UUID> {
}