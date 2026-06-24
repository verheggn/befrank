package nl.befrank.mappers;

import nl.befrank.model.Deelnemer;
import nl.befrank.model.DeelnemerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DeelnemerMapper {
    DeelnemerDto toDto(Deelnemer deelnemer);

    @Mapping(target = "dienstverband", ignore = true)
    @Mapping(target = "pensioenrekening", ignore = true)
    Deelnemer toEntity(DeelnemerDto dto);

}
