package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Services.ProjectService;

@CrossOrigin("*")
@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/project/{id}")
    public Project getProjectById(@PathVariable String id) {
        return projectService.getProjectById(id);
    }

    @GetMapping("/projects")
    public List<Project> getProjects() {
        return projectService.getProjects();
    }

    @PostMapping("/project")
    public Project createProject(@RequestBody Project project) {
        return projectService.createProjects(project);
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

    public Project addUserToProject() {
        //invite kanppen, detta ska lägga till användare i projectet
        //lägg till ett strängat id till userids i projects
        return null;
    }
    public Project deleteUserAccess() {
        //Detta är att detta ska ta bort en användare från projectet. 
        return null;
    }
    
}
