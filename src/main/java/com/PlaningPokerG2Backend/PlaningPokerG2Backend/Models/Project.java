package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Projects")
public class Project {
    @Id
    private String projektId;
    private String projectname;
    private List<String> userIds;
    private List<Issues> issues;
    private boolean active;

    //Skapar listorna direkt i konstruktorn istället för att ta in som input.
    //setters för userids och issues omgjorda till add funktioner.
    public Project(String projektId, String projectname, boolean active) {
        this.projektId = projektId;
        this.projectname = projectname;
        this.userIds = new ArrayList<String>();
        this.issues = new ArrayList<Issues>();
        this.active = active;
    }

    public String getProjektId() {
        return projektId;
    }

    public void setProjektId(String projektId) {
        this.projektId = projektId;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void addUserIds(String userIds) {
        this.userIds.add(userIds);
    }

    public List<Issues> getIssues() {
        return issues;
    }

    public void addIssues(Issues issues) {
        this.issues.add(issues);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
