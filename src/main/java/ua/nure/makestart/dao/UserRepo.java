package ua.nure.makestart.dao;

import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.nure.makestart.model.Users;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepo extends CrudRepository<Users, UUID> {
    @Transactional
    Optional<Users> findUsersByUsername(String username);

    @Transactional
    Users findUsersByPassword(String password);
}