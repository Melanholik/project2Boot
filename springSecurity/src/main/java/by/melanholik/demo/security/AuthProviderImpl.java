package by.melanholik.demo.security;

import by.melanholik.demo.service.UsersDetailsService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AuthProviderImpl implements AuthenticationProvider {

    private final UsersDetailsService usersDetailsService;

    public AuthProviderImpl(UsersDetailsService usersDetailsService) {
        this.usersDetailsService = usersDetailsService;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        UsersDetails usersDetails = usersDetailsService.loadUserByUsername(name);
        String password = authentication.getCredentials().toString();
        if (!password.equals(usersDetails.getPassword())){
            throw new BadCredentialsException("Incorrect password");
        }
        return new UsernamePasswordAuthenticationToken(usersDetails, password, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
