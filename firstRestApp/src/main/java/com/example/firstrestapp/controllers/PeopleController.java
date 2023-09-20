package com.example.firstrestapp.controllers;

import com.example.firstrestapp.DTO.PersonDTO;
import com.example.firstrestapp.models.Person;
import com.example.firstrestapp.services.PersonService;
import com.example.firstrestapp.util.PersonErrorResponse;
import com.example.firstrestapp.util.PersonNotCreatedException;
import com.example.firstrestapp.util.PersonNotFoundException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/people")
public class PeopleController {
    private final PersonService personService;
    private final ModelMapper modelMapper;

    @Autowired
    public PeopleController(PersonService personService, ModelMapper modelMapper) {
        this.personService = personService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping
    public List<PersonDTO> getAll() {
        return personService.getAll().stream().map(this::convertToPersonDTO)
                .collect(Collectors.toList());
    }

    @RequestMapping("/{id}")
    public PersonDTO getById(@PathVariable Integer id) {
        return convertToPersonDTO(personService.findById(id));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@Valid @RequestBody PersonDTO personDto,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append("-").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new PersonNotCreatedException(errorMsg.toString());
        }
        personService.save(convertToPerson(personDto));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Person convertToPerson(PersonDTO personDto) {
        return modelMapper.map(personDto, Person.class);
    }

    private PersonDTO convertToPersonDTO(Person person) {
        return modelMapper.map(person, PersonDTO.class);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException e) {
        PersonErrorResponse response = new PersonErrorResponse(
                "Person with this id wasn't found", System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleExceptionNotCreated(PersonNotCreatedException e) {
        PersonErrorResponse response = new PersonErrorResponse(
                e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
