package com.example.firstrestapp.controllers;

import com.example.firstrestapp.models.Person;
import com.example.firstrestapp.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController {
    private final PersonService personService;

    @Autowired
    public PeopleController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping
    public List<Person> getAll() {
        return personService.getAll();
    }

    @RequestMapping("/{id}")
    public Person getById(@PathVariable Integer id) {
        return personService.findById(id);
    }
}
