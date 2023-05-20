package ua.nure.makestart.mapper;

import org.mapstruct.Mapper;
import ua.nure.makestart.dto.CvInfoDto;
import ua.nure.makestart.model.Cv;


@Mapper(componentModel = "spring", uses = LanguagesMapper.class)
public interface CvMapper {

    CvInfoDto toCvInfoModel(Cv cv);
}
