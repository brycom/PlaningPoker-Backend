package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Services;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Role;

@Service
public class RoleService {

    private final MongoOperations mongoOperations;

    public RoleService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public String getRole(String id) {
        return mongoOperations.findById(id, Role.class).getAuthority();
    }

    public Role createRole(Role role) {
        return mongoOperations.insert(role);
    }

    public List<Role> getRoles() {
        return mongoOperations.findAll(Role.class);
    }

}
