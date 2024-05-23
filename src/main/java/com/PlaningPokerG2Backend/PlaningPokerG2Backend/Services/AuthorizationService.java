package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Role;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.User;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.DTOs.LoginResponsDTO;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.DTOs.UserDTO;

@Service
@Transactional
public class AuthorizationService {
    private AuthenticationManager authenticationManager;
    private MongoOperations mongoOperations;
    private PasswordEncoder passwordEncoder;
    private TokenService tokenService;

    public AuthorizationService(MongoOperations mongoOperations, AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            TokenService tokenService) {
        this.mongoOperations = mongoOperations;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public UserDTO createUser(User user) throws Exception {
        Query q = Query.query(Criteria.where("username").is(user.getUsername()));
        User u = mongoOperations.findOne(q, User.class);
        if (u == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            Query query = Query.query(Criteria.where("authority").is("user"));
            Role role = mongoOperations.findOne(query, Role.class);
            Set<Role> roles = new HashSet<Role>();
            roles.add(role);
            user.addRole(role);
            mongoOperations.insert(user);
            return new UserDTO(user.getUsername(), user.getFirstName(), null);
        } else
            throw new Exception("Username already in use");

    }

    public LoginResponsDTO login(String username, String password) {

        /*  try { */
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        System.out.println(auth);

        String token = tokenService.jwtGenerator(auth);
        System.out.println(token);
        Query query = Query.query(Criteria.where("username").is(username));
        User user = mongoOperations.findOne(query, User.class);
        UserDTO userDTO = new UserDTO(user.getUsername(), user.getFirstName(), user.getUserId());

        return new LoginResponsDTO(userDTO, token);

        /*         } catch (AuthenticationException e) {
            return new LoginResponsDTO(null, "Felaktigt användarnamn eller lösenord");
        }
         */
    }

}
