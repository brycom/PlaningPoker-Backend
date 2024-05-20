package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Role;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Services.RoleService;

@RestController
@RequestMapping("/role")
@CrossOrigin("*")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    public Iterable<Role> getAllRoles() {
        return roleService.getRoles();
    }

    @PostMapping("/create")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        try {
            return ResponseEntity.ok(roleService.createRole(role));

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest().body(new Role(e.getMessage()));
        }
    }

    @PostMapping("/add/{userId}/{roleId}")
    public ResponseEntity<String> addRole(@PathVariable String roleId, @PathVariable String userId) {
        try {
            return ResponseEntity.ok(roleService.addRole(roleId, userId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{userId}/{roleId}")
    public ResponseEntity<String> editRole(@PathVariable String roleId, @PathVariable String userId) {
        try {
            return ResponseEntity.ok(roleService.editUserRole(roleId, userId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @DeleteMapping("/{userId}/{roleId}")
    public ResponseEntity<String> deleteRole(@PathVariable String roleId, @PathVariable String userId) {
        try {
            return ResponseEntity.ok(roleService.deleteRole(roleId, userId));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
