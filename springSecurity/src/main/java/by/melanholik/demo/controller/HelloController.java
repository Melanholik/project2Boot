package by.melanholik.demo.controller;

import by.melanholik.demo.security.UsersDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String getHello(){
        return "hello";
    }

    @GetMapping("/showUserInfo")
    public String showUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsersDetails usersDetails = (UsersDetails) authentication.getPrincipal();
        System.out.println(usersDetails.getUser());
        return "hello";
    }
}
