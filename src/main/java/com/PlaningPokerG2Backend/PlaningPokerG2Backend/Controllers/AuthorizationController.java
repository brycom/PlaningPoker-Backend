package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.User;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.DTOs.LoginResponsDTO;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.DTOs.UserDTO;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Services.AuthorizationService;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {

    @Autowired
    private AuthorizationService authorizationService;

    @PostMapping("/login")
    public LoginResponsDTO authenticateUser(@RequestBody User user) {
        System.out.println("Username: " + user.getUsername() + " Password: " + user.getPassword());
        return authorizationService.login(user.getUsername(), user.getPassword());
    }

    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody User user) throws Exception {
        return authorizationService.createUser(user);
    }
}
