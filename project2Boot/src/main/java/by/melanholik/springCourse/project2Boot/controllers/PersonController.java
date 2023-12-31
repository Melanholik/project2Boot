package by.melanholik.springCourse.project2Boot.controllers;

import by.melanholik.springCourse.project2Boot.models.Person;
import by.melanholik.springCourse.project2Boot.services.PersonService;
import by.melanholik.springCourse.project2Boot.util.PersonValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonValidator personValidator;
    private final PersonService personService;

    @Autowired
    public PersonController(PersonValidator personValidator, PersonService personService) {
        this.personValidator = personValidator;
        this.personService = personService;
    }

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("people", personService.findAll());
        return "/person/people";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("person") Person person) {
        return "/person/new";
    }

    @PostMapping()
    public String add(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {

        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/person/new";
        }
        personService.add(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    private String getById(@PathVariable int id, Model model) {
        Optional<Person> person = personService.getById(id);
        if (person.isEmpty()) {
            model.addAttribute("id", id);
            return "/person/notFound";
        }
        model.addAttribute("person", person.get());
        model.addAttribute("books", person.get().getList());
        return "/person/person";
    }

    @DeleteMapping("/{id}")
    private String deleteById(@PathVariable int id) {
        personService.deleteById(id);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    private String getByIdForEdit(@PathVariable int id, Model model) {
        Optional<Person> person = personService.getById(id);
        if (person.isEmpty()) {
            model.addAttribute("id", id);
            return "/person/notFound";
        }
        model.addAttribute("person", person.get());
        return "/person/edit";
    }

    @PatchMapping("/{id}")
    private String change(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {

        personValidator.validateWhenPersonChanged(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/person/edit";
        }
        personService.update(person);
        return "redirect:/people";
    }
}
