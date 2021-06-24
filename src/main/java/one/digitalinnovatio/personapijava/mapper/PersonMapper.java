package one.digitalinnovatio.personapijava.mapper;


import one.digitalinnovatio.personapijava.dto.request.PersonDTO;
import one.digitalinnovatio.personapijava.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {
   PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

   @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
   Person toModel(PersonDTO personDTO);

   PersonDTO toDTO(Person person);

}
