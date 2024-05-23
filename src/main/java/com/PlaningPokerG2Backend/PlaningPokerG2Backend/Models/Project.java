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
    private List<Issue> issues;
    private boolean active;

    //Skapar listorna direkt i konstruktorn istället för att ta in som input.
    //setters för userids och issues omgjorda till add funktioner.
    public Project(String projektId, String projectname, boolean active) {
        this.projektId = projektId;
        this.projectname = projectname;
        this.userIds = new ArrayList<String>();
        this.issues = new ArrayList<Issue>();
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

    public List<Issue> getIssues() {
        return issues;
    }

    public void addIssues(Issue issues) {
        this.issues.add(issues);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

        // Helper method to calculate average votes per issue
        public double getAverageVotesPerIssue() {
            if (issues != null && !issues.isEmpty()) {
                double totalVotes = issues.stream().flatMap(issue -> issue.getVotes().stream()).count();
                return totalVotes / issues.size();
            }
            return 0;
        }
}
