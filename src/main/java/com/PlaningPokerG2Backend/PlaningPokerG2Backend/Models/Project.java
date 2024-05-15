package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Projects")
public class Project {
    @Id
    private String id;
    private String projectName;
    private List <String> userIds;
    private List <Issues> issues;
    private boolean active;
    
    public Project(String id, String projectName, List<String> userIds, List<Issues> issues, boolean active) {
        this.id = id;
        this.projectName = projectName;
        this.userIds = userIds;
        this.issues = issues;
        this.active = active;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public List<Issues> getIssues() {
        return issues;
    }

    public void setIssues(List<Issues> issues) {
        this.issues = issues;
    }


    public boolean isActive() {
        return active;
    }


    public void setActive(boolean active) {
        this.active = active;
    }

    
}
