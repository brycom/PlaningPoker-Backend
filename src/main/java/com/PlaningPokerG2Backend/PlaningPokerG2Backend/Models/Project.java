package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models;

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

    public Project(String projektId, String projectname, List<String> userIds, List<Issues> issues, boolean active) {
        this.projektId = projektId;
        this.projectname = projectname;
        this.userIds = userIds;
        this.issues = issues;
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

    // Helper method to calculate average votes per issue
    public double getAverageVotesPerIssue() {
        if (issues != null && !issues.isEmpty()) {
            double totalVotes = issues.stream().flatMap(issue -> issue.getVotes().stream()).count();
            return totalVotes / issues.size();
        }
        return 0;
    }

}
