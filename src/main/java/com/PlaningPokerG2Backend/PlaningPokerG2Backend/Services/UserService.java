package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Services;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.User;
//import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Repositories.UserRepository;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.DTOs.UserDTO;

// 
@Service
public class UserService implements UserDetailsService {
    private MongoOperations mongoOperations;
    private final Map<String, User> userStore = new ConcurrentHashMap<>();

    public UserService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;

    }

    /*   @Autowired
    private UserRepository userRepository; */

    /*     public List<User> getAllUsers() {
        return new ArrayList<>(userStore.values());
    } */

    public /* Optional<User>  */UserDTO getUserById(String id) {
        /* return Optional.ofNullable(userStore.get(id)); */
        User user = mongoOperations.findById(id, User.class);
        return new UserDTO(user.getUsername(), user.getFirstName(), user.getId());
    }

    /*     public User createUser(User user) {
        if (user.getId() == null || user.getId().isEmpty()) {
            user.setId(UUID.randomUUID().toString());
        }
        userStore.put(user.getId(), user);
        return user;
    } */

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

    /*     public boolean existsByUserName(String userName) {
        return userRepository.findByUserName(userName).isPresent();
    } */
}
