package by.melanholik.demo.service;

import by.melanholik.demo.model.User;
import by.melanholik.demo.repository.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.Optional;

@Service
public class UsersService {
    private final PasswordEncoder passwordEncoder;
    private final UsersRepository usersRepository;

    public UsersService(PasswordEncoder passwordEncoder, UsersRepository usersRepository) {
        this.passwordEncoder = passwordEncoder;
        this.usersRepository = usersRepository;
    }

    public Optional<User> getByName(String name) {
        return usersRepository.findByName(name);
    }

    @Transient
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        usersRepository.save(user);
    }
}
