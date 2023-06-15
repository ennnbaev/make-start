package ua.nure.makestart.mapper;

import org.mapstruct.Mapper;
import ua.nure.makestart.dto.CvInfoDto;
import ua.nure.makestart.model.Cv;

import java.util.Set;


@Mapper(componentModel = "spring", uses = {LanguagesMapper.class, UserMapper.class})
public interface CvMapper {

    CvInfoDto toCvInfoModel(Cv cv);

    Set<CvInfoDto> toCvInfoModelSet(Set<Cv> cv);
}
