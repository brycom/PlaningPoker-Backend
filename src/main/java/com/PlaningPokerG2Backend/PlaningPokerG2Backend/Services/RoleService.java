package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Services;

import java.util.List;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Role;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.User;

@Service
public class RoleService {

    private final MongoOperations mongoOperations;

    public RoleService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public Role getRole(String id) {
        Role role = mongoOperations.findById(id, Role.class);
        if (role == null) {
            throw new IllegalArgumentException("Rollen finns inte");
        }
        return role;
    }

    public Role createRole(Role role) {
        List<Role> roles = mongoOperations.findAll(Role.class);
        for (Role r : roles) {
            if (r.getAuthority().equals(role.getAuthority())) {
                throw new IllegalArgumentException("Rollen: " + role.getAuthority() + " finns redan");
            }

        }
        if (role == null || role.getAuthority().equals("")) {
            throw new IllegalArgumentException("Authority måste vara ifylld");
        }
        return mongoOperations.insert(role);
    }

    public List<Role> getRoles() {
        return mongoOperations.findAll(Role.class);
    }

    public String editUserRole(String roleId, String userID) {
        User user = mongoOperations.findById(userID, User.class);
        Role newRole = mongoOperations.findById(roleId, Role.class);
        if (user == null) {
            throw new IllegalArgumentException("Användaren kunde inte hittas");
        }
        if (newRole == null) {
            throw new IllegalArgumentException("Rollen kunde inte hittas");
        }
        for (Role role : user.getRole()) {
            if (role.getRoleId().equals(role.getRoleId())) {
                throw new IllegalArgumentException(user.getUsername() + " har redan rollen: " + newRole.getAuthority());

            }

        }

        user.switchRole(newRole);
        mongoOperations.save(user);
        return "Din nya rol är: " + newRole.getAuthority();

    }

    public String addRole(String roleId, String userId) {
        User user = mongoOperations.findById(userId, User.class);
        Role newRole = mongoOperations.findById(roleId, Role.class);
        if (user == null) {
            throw new IllegalArgumentException("Användaren kunde inte hittas");
        }
        if (newRole == null) {
            throw new IllegalArgumentException("Rollen kunde inte hittas");

        }
        user.addRole(newRole);
        if (user.getRole().contains(newRole)) {
            mongoOperations.save(user);
            return "Rollen: " + newRole.getAuthority() + " är tillagd til användare:" + user.getUsername();
        } else {
            throw new RuntimeException("Rollen: " + newRole.getAuthority() + " kunde inte läggas till");
        }
    }

    public String deleteRole(String roleId, String userId) {
        User user = mongoOperations.findById(userId, User.class);
        Role oldRole = mongoOperations.findById(roleId, Role.class);
        if (user == null) {
            throw new IllegalArgumentException("Användaren kunde inte hittas");
        }
        if (oldRole == null) {
            throw new IllegalArgumentException("Rollen kunde inte hittas");
        }
        user.removeRole(oldRole);

        mongoOperations.save(user);
        return "Rollen: " + oldRole.getAuthority() + " är borttagen från användare:" + user.getUsername();

    }

}
