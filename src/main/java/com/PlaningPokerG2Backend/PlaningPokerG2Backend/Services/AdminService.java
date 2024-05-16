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

    public Role editRole(int id, Role role) {
        role.setId(id);
        return mongoOperations.save(role);
    }

    public User addUser(User user) {
        return mongoOperations.insert(user);
    }
}
