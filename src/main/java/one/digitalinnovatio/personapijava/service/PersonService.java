package one.digitalinnovatio.personapijava.service;

import one.digitalinnovatio.personapijava.dto.message.MessageResponseDTO;
import one.digitalinnovatio.personapijava.entity.Person;
import one.digitalinnovatio.personapijava.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(Person person){
        Person savedPerson = personRepository.save(person);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + savedPerson.getId())
                .build();
    }
}
