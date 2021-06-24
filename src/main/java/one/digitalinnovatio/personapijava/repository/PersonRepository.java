package one.digitalinnovatio.personapijava.repository;

import one.digitalinnovatio.personapijava.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
