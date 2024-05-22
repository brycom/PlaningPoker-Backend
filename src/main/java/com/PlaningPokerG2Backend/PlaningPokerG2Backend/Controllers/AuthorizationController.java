package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.User;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.DTOs.LoginResponsDTO;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.DTOs.UserDTO;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Services.AuthorizationService;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthorizationController {

    @Autowired
    private AuthorizationService authorizationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponsDTO> authenticateUser(@RequestBody User user) {
        try {
            return ResponseEntity.ok().body(authorizationService.login(user.getUsername(), user.getPassword()));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponsDTO());
        }

    }

    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody User user) throws Exception {
        return authorizationService.createUser(user);
    }
}
