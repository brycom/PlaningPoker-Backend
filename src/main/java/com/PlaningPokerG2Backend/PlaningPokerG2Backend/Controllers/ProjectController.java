package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Project;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.User;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Services.ProjectService;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Services.TokenService;

@RestController
@RequestMapping("/project")
@CrossOrigin("*")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TokenService tokenService;

    @GetMapping("/project/{id}")
    public Project getProjectById(@PathVariable String id) {
        return projectService.getProjectById(id);
    }

    @GetMapping("/projects")
    public List<Project> getProjects(@AuthenticationPrincipal Jwt jwt) {
        System.out.println("Steg 1");
        String user = tokenService.getUserFromToken(jwt);
        System.out.println(user);
        return projectService.getProjects(user);
    }

    @PostMapping("/project")
    public Project createProject(@AuthenticationPrincipal Jwt jwt, @RequestBody Project project) {
        String user = tokenService.getUserFromToken(jwt);
        return projectService.createProjects(project, user);
    }

    @DeleteMapping("/project/{id}")
    public String deleteProject(@PathVariable String id) {

        projectService.deleteProject(id);
        return "Deleted: " + id;
    }

    @PatchMapping("/project/{id}")
    public Project editProject(@PathVariable String id, @RequestBody Project project) {
        return projectService.editProjects(id, project);
    }

    @GetMapping("/{projectId}/users")
    public ResponseEntity<List<User>> getUsersInProject(@PathVariable String projectId) {
        List<User> users = projectService.getUsersInProject(projectId);
        if (users == null || users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(users);
    }

    @PostMapping("/projects/addUser")
    public ResponseEntity<Project> addUserToProject(@RequestBody Map<String, String> requestData) {
        String projectId = requestData.get("projektId");
        String userId = requestData.get("userId");
        Project updatedProject = projectService.addUserToProject(projectId, userId);
        if (updatedProject == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/projects/deleteUser")
    public ResponseEntity<Project> deleteUserFromProject(@RequestBody Map<String, String> requestData) {
        String projectId = requestData.get("projektId");
        String userId = requestData.get("userId");
        Project updatedProject = projectService.deleteUserFromProject(projectId, userId);
        if (updatedProject == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedProject);
    }

}
