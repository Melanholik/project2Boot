package com.example.firstrestapp.controllers;

import com.example.firstrestapp.models.Person;
import com.example.firstrestapp.services.PersonService;
import com.example.firstrestapp.util.PersonErrorResponse;
import com.example.firstrestapp.util.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException e) {
        PersonErrorResponse response = new PersonErrorResponse(
                "Person with this id wasn't found", System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }
}
