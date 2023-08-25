package by.melanholik.demo.service;

import by.melanholik.demo.model.User;
import by.melanholik.demo.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Optional<User> getByName(String name) {
        return usersRepository.findByName(name);
    }

    public void add(User user) {
        usersRepository.save(user);
    }
}
