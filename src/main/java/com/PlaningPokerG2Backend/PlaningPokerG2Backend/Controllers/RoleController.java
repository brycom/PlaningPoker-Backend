package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Role;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Services.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/adrole")
    public Role createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }

}
