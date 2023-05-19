package ua.nure.makestart.dao;

import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.nure.makestart.model.Languages;

@Repository
public interface LanguageRepo extends CrudRepository<Languages, String> {
    @Transactional
    Languages findLanguagesByLanguageName(String languageName);
}
