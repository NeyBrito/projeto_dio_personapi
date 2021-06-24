package one.digitalinnovatio.personapijava.service;

import lombok.AllArgsConstructor;
import one.digitalinnovatio.personapijava.dto.MessageResponseDTO;
import one.digitalinnovatio.personapijava.dto.request.PersonDTO;
import one.digitalinnovatio.personapijava.entity.Person;
import one.digitalinnovatio.personapijava.exception.PersonNotFoundException.PersonNotFoundException;
import one.digitalinnovatio.personapijava.mapper.PersonMapper;
import one.digitalinnovatio.personapijava.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.mapstruct.Mapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private PersonRepository personRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person personToSave = PersonMapper.INSTANCE.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return createMessageResponse(savedPerson.getId(), "Created person with ID ");
    }
    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }
    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyIdExists(id);
        return personMapper.toDTO(person);
    }
    public void delete(Long id) throws PersonNotFoundException{
        verifyIdExists(id);
        personRepository.deleteById(id);
    }
    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIdExists(id);
        Person personToUpdate = PersonMapper.INSTANCE.toModel(personDTO);
        Person uptatePerson = personRepository.save(personToUpdate);
        return createMessageResponse(uptatePerson.getId(), "Updated person with ID ");
    }
    private Person verifyIdExists(Long id) throws PersonNotFoundException{
        return personRepository.findById(id)
                .orElseThrow(()-> new PersonNotFoundException(id));
    }
    private MessageResponseDTO createMessageResponse(Long id, String s) {
        return MessageResponseDTO
                .builder()
                .message(s + id)
                .build();
    }
}
