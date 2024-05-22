package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Issues;

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

    public Issues addIssue(Issues issues) throws Exception {
        if (!isIssueNameUnique(issues.getIssuename())) {
            throw new Exception("Issuename finns redan");
        }
        return mongoOperations.insert(issues);
    }

    public List<Issues> getIssues() {
        return mongoOperations.findAll(Issues.class);
    }

    public Issues getIssueById(UUID issueId) {
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

    public Issues closeIssue(UUID id) {
        if (getIssueById(id) == null) {
            throw new IllegalArgumentException("Issue not found");
        }
        Issues issue = getIssueById(id);

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
        mongoOperations.save(issue);
        return issue;
    }
}
