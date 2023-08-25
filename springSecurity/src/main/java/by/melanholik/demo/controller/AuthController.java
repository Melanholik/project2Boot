package by.melanholik.demo.controller;

import by.melanholik.demo.model.User;
import by.melanholik.demo.service.UsersService;
import by.melanholik.demo.util.UsersValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UsersValidation usersValidation;
    private final UsersService usersService;

    @Autowired
    public AuthController(UsersValidation usersValidation, UsersService usersService) {
        this.usersValidation = usersValidation;
        this.usersService = usersService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute User user) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute @Valid User user, BindingResult bindingResult) {
        usersValidation.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "auth/registration";
        }
        usersService.add(user);
        return "redirect:/auth/login";
    }
}
