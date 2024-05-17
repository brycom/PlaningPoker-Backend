package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Controllers;

import java.util.List;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Issues;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Project;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Role;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.User;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Services.AdminService;


@RestController
@RequestMapping("/admin")
public class AdminController {
    
    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    
    @GetMapping("/users")
    public List<User> getUsers() {
        return adminService.getUsers();
    }

    @GetMapping("/projects") 
    public List<Project> getProjects() {
        return adminService.getProjects();
    }

    
    
    @GetMapping("/issues")
    public List<Issues> getIssues() {
        return adminService.getIssues();
    }

    
    @PatchMapping("/role/{id}") 
        public Role editRole(@PathVariable int id, @RequestBody Role role) {
            return adminService.editRole(id, role);
        }


    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        return adminService.addUser(user);
    }
    
}
