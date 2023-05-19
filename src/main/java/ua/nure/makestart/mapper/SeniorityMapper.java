package ua.nure.makestart.mapper;

import org.mapstruct.Mapper;
import ua.nure.makestart.dto.SeniorityDto;
import ua.nure.makestart.model.Seniority;

@Mapper(componentModel = "spring")
public interface SeniorityMapper {
    SeniorityDto toDto(Seniority seniority);

    Seniority toModel(SeniorityDto seniorityDto);
}
