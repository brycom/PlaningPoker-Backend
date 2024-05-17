package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Services;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.User;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Repositories.UserRepository;

@Service
public class UserService {

    private final Map<String, User> userStore = new ConcurrentHashMap<>();

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return new ArrayList<>(userStore.values());
    }

    public Optional<User> getUserById(String id) {
        return Optional.ofNullable(userStore.get(id));
    }

    public User createUser(User user) {
        if (user.getId() == null || user.getId().isEmpty()) {
            user.setId(UUID.randomUUID().toString());
        }
        userStore.put(user.getId(), user);
        return user;
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

    public boolean existsByUserName(String userName) {
        return userRepository.findByUserName(userName).isPresent();
    }
}
