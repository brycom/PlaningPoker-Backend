package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Services;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Issues;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Project;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Role;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.User;

@Service
public class AdminService {
    private final MongoOperations mongoOperations;

    public AdminService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public List<User> getUsers() {
        return mongoOperations.findAll(User.class);
    }

    public List<Project> getProjects() {
        return mongoOperations.findAll(Project.class);
    }

    public List<Issues> getIssues() {
        return mongoOperations.findAll(Issues.class);
    }

    public String editRole(int roleId, String userID) {
        User user = mongoOperations.findById(userID, User.class);
        Role newRole = mongoOperations.findById(roleId, Role.class);
        mongoOperations.save(user);

        return "Din nya rol är: " + newRole.getAuthority();
    }

    public User addUser(User user) {
        return mongoOperations.insert(user);
    }
}
