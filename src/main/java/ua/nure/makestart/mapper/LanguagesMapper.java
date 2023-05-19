package ua.nure.makestart.mapper;

import org.mapstruct.Mapper;
import ua.nure.makestart.dto.LanguageDto;
import ua.nure.makestart.model.Languages;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface LanguagesMapper {
    LanguageDto toDto(Languages languages);

    Languages toModel(LanguageDto languageDto);

    Set<LanguageDto> toSetDto(Set<Languages> languagesSet);

    Set<Languages> toSetModel(Set<LanguageDto> languagesSet);
}
