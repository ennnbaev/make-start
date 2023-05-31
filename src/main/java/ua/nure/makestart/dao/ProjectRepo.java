package ua.nure.makestart.dao;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.nure.makestart.model.Project;

import java.util.UUID;

@Repository
@Transactional
public interface ProjectRepo extends CrudRepository<Project, UUID> {
    @Modifying
    @Query(nativeQuery = true, value = "insert into Project values (:id,:projectName,:description,null,:price," +
            "(select user_id from users where username = :username))")
    void saveProjectByUsername(String id, String projectName, String description, double price, String username);

    Page<Project> findAll(Pageable pageable);

    void deleteByProjectName(String projectName);

    boolean existsByProjectNameAndOwner_Username(String projectName, String username);

    default void saveProjectByUsername(String username, Project project) {
        this.saveProjectByUsername(project.getId(), project.getProjectName(), project.getDescription(), project.getPrice(), username);
    }

    Project getProjectsByProjectName(String projectName);
}
