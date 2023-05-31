package ua.nure.makestart.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = LanguagesMapper.class)
public interface PositionMapper {
}
