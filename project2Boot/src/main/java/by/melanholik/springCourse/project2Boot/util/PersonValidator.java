package by.melanholik.springCourse.project2Boot.util;

import by.melanholik.springCourse.project2Boot.models.Person;
import by.melanholik.springCourse.project2Boot.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;


@Component
public class PersonValidator implements Validator {
    private final PersonRepository personRepository;

    @Autowired
    public PersonValidator(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if (personRepository.findByName(person.getName()).isPresent()) {
            errors.rejectValue("name", "", "This name is already taken");
        }
    }

    public void validateWhenPersonChanged(Object target, Errors errors) {
        Person person = (Person) target;
        Optional<Person> oldPerson = personRepository.findByName(person.getName());
        if (oldPerson.isPresent()) {
            if (oldPerson.get().getId() != person.getId()) {
                errors.rejectValue("name", "", "This name is already taken");
            }
        }
    }
}
