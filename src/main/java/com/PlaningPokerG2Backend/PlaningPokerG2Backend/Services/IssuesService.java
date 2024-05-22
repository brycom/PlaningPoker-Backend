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

import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Issues;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Project;

@Service
public class IssuesService {

    private final MongoOperations mongoOperations;

    public IssuesService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public boolean isIssueNameUnique(String issueName) {
        Query query = new Query(Criteria.where("issuename").is(issueName));
        return mongoOperations.find(query, Issues.class).isEmpty();
    }

    public Issues addIssue(String projektId, Issues issue) throws Exception {
        Project projekt = mongoOperations.findById(projektId, Project.class);
        if (projekt == null) {
            throw new NameNotFoundException("Projekt finns inte");
        }
        for (Issues i : projekt.getIssues()) {
            if (!isIssueNameUnique(i.getIssuename())) {
                throw new Exception("Issuename finns redan");
            }

        }
        projekt.addIssues(issue);
        mongoOperations.save(projekt);
        return issue;
    }

    public List<Issues> getIssues(String projectId) throws Exception {
        List<Issues> issues = mongoOperations.findById(projectId, Project.class).getIssues();

        if (issues == null) {
            throw new NameNotFoundException("Projekt finns inte");

        }

        return issues;
    }

    public Issues getIssueById(String issueId) {
        Query query = new Query(Criteria.where("issueId").is(issueId));
        Issues existingIssue = mongoOperations.findOne(query, Issues.class);
        return existingIssue;
    }

    public Issues updateIssue(Issues updatedIssue) {
        return mongoOperations.save(updatedIssue);
    }

    public void deleteIssue(UUID issueId) {
        Query query = new Query(Criteria.where("issueId").is(issueId));
        mongoOperations.remove(query, Issues.class);

    }

    public Issues closeIssue(String projectId, String issueId) throws Exception {
        Project project = mongoOperations.findById(projectId, Project.class);
        if (project == null) {
            throw new IllegalArgumentException("Projekt finns inte");
        }
        Issues issue = project.getIssues().stream().filter(is -> is.getIssueId().equals(issueId)).findFirst()
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
