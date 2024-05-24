package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.naming.NameNotFoundException;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Issue;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Project;

@Service
public class IssuesService {

    private final MongoOperations mongoOperations;

    public IssuesService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public boolean isIssueNameUnique(String issueName) {
        Query query = new Query(Criteria.where("issuename").is(issueName));
        return mongoOperations.find(query, Issue.class).isEmpty();
    }

    public Issue addIssue(String user, String projectId, Issue issue) throws Exception {
        Project project = mongoOperations.findById(projectId, Project.class);
        if (project == null) {
            throw new NameNotFoundException("Projekt finns inte");
        }
        if (!project.getUserIds().contains(user)) {
            throw new IllegalArgumentException("Du har inte tillgång till detta projekt");
        }
        for (Issue i : project.getIssues()) {
            if (!isIssueNameUnique(i.getIssuename())) {
                throw new Exception("Issuename finns redan");
            }

        }
        project.addIssues(issue);
        mongoOperations.save(project);
        return issue;
    }

    public List<Issue> getIssues(String user, String projectId) throws Exception {
        Project project = mongoOperations.findById(projectId, Project.class);
        if (project == null) {
            throw new NameNotFoundException("Projekt finns inte");
        }
        if (!project.getUserIds().contains(user)) {
            throw new IllegalArgumentException("Du har inte tillgång till detta projekt");

        }
        List<Issue> issues = project.getIssues();

        if (issues == null) {
            throw new NameNotFoundException("Projekt finns inte");

        }

        return issues;
    }

    public Issue getIssueById(String issueId) {
        Query query = new Query(Criteria.where("issueId").is(issueId));
        Issue existingIssue = mongoOperations.findOne(query, Issue.class);
        return existingIssue;
    }

    public Issue updateIssue(String user, String projectId, String issueId, Issue newIssue) throws Exception {
        Project project = mongoOperations.findById(projectId, Project.class);
        if (project.getUserIds().contains(user)) {
            Issue issue = project.getIssues().stream().filter(is -> is.getIssueId().equals(issueId)).findFirst()
                    .orElseThrow(() -> new Exception("issuet fins inte"));
            if ((!issue.getIssuename().equals(newIssue.getIssuename()) && newIssue.getIssuename() != null)) {
                issue.setIssuename(newIssue.getIssuename());
                mongoOperations.save(project);
            }

        } else {
            throw new IllegalArgumentException("Du har inte tillgång till detta projekt");
        }
        return newIssue;

    }

    public void deleteIssue(String user, String projectId, String issueId) throws Exception {
        Project project = mongoOperations.findById(projectId, Project.class);
        if (project == null) {
            throw new NameNotFoundException("Projekt finns inte");
        }
        if (!project.getUserIds().contains(user)) {
            throw new IllegalArgumentException("Du har inte tillgång till detta projekt");

        }
        List<Issue> issues = project.getIssues();
        issues.removeIf(is -> is.getIssueId().equals(issueId));
        mongoOperations.save(project);

    }

    public Issue closeIssue(String user, String projectId, String issueId) throws Exception {
        Project project = mongoOperations.findById(projectId, Project.class);
        if (project == null) {
            throw new IllegalArgumentException("Projekt finns inte");
        }
        if (!project.getUserIds().contains(user)) {
            throw new IllegalArgumentException("Du har inte tillgång till detta projekt");

        }
        Issue issue = project.getIssues().stream().filter(is -> is.getIssueId().equals(issueId)).findFirst()
                .orElseThrow(() -> new Exception("issuet fins inte"));

        LocalDateTime startTime = issue.getStartTime();
        LocalDateTime endTime = LocalDateTime.now();
        Duration duration = Duration.between(startTime, endTime);
        long actualTimeInSeconds = duration.getSeconds();

        long hours = actualTimeInSeconds / 3600;
        long minutes = (actualTimeInSeconds % 3600) / 60;
        long seconds = actualTimeInSeconds % 60;

        String formattedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        issue.setActualTime(formattedTime);

        issue.setEndTime(endTime);
        mongoOperations.save(project);
        return issue;
    }
}
