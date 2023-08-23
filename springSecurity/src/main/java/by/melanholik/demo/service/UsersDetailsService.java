package by.melanholik.demo.service;

import by.melanholik.demo.model.User;
import by.melanholik.demo.repository.UsersRepository;
import by.melanholik.demo.security.UsersDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UsersDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = usersRepository.findByName(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found!");
        }
        return new UsersDetails(user.get());
    }
}
