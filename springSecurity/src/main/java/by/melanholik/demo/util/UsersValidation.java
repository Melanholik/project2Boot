package by.melanholik.demo.util;

import by.melanholik.demo.model.User;
import by.melanholik.demo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class UsersValidation implements Validator {

    private final UsersService usersService;

    @Autowired
    public UsersValidation(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User currentUser = (User) target;
        Optional<User> user = usersService.getByName(currentUser.getName());
        if (user.isPresent()) {
            errors.rejectValue("name", "", "Имя не уникально");
        }
    }
}
