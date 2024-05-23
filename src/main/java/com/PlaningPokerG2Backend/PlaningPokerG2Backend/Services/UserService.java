package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Services;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.User;

import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.DTOs.UserDTO;

@Service
public class UserService implements UserDetailsService {
    private MongoOperations mongoOperations;
    private final Map<String, User> userStore = new ConcurrentHashMap<>();

    public UserService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;

    }

    public UserDTO getUserById(String id) {
        User user = mongoOperations.findById(id, User.class);
        if (user == null) {
            return null;

        } else {
            return convertToUserDTO(user);
        }
    }

    public User updateUser(String id, User user) {
        if (userStore.containsKey(id)) {
            user.setId(id);
            userStore.put(id, user);
            return user;
        } else {
            return null;
        }
    }

    public void deleteUser(String id) {
        userStore.remove(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Query query = Query.query(Criteria.where("username").is(username));
        User user = mongoOperations.findOne(query, User.class);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public User getUserByUsername(String username) throws UsernameNotFoundException {
        Query query = Query.query(Criteria.where("username").is(username));
        User user = mongoOperations.findOne(query, User.class);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    private UserDTO convertToUserDTO(User user) {
        return new UserDTO(user.getUsername(), user.getFirstName(), user.getUserId());
    }
}
