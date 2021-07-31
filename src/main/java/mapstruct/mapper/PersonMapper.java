package mapstruct.mapper;

import mapstruct.Person;
import mapstruct.dto.PersonDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(source = "address", target = "addressDto")
    PersonDto personToPersonDto(Person person);

    @Mapping(source = "addressDto", target = "address")
    Person personDtoToPerson(PersonDto personDto);
}
