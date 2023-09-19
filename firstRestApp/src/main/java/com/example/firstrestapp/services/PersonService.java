package com.example.firstrestapp.services;

import com.example.firstrestapp.models.Person;
import com.example.firstrestapp.repositoryes.PersonRepository;
import com.example.firstrestapp.util.PersonNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public Person findById(Integer id) {
        Optional<Person> person = personRepository.findById(id);
        return person.orElseThrow(PersonNotFoundException::new);
    }

    @Transactional
    public void save(Person person) {
        personRepository.save(person);
    }
}
