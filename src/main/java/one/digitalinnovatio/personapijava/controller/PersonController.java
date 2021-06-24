package one.digitalinnovatio.personapijava.controller;

import one.digitalinnovatio.personapijava.entity.Person;
import one.digitalinnovatio.personapijava.dto.message.MessageResponseDTO;
import one.digitalinnovatio.personapijava.repository.PersonRepository;
import one.digitalinnovatio.personapijava.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/people")
public class PersonController {
    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody Person person){
        return personService.createPerson(person);

    }

}
