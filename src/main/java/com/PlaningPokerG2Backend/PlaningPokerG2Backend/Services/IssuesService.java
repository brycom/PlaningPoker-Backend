package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Issue;

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

    public Issue addIssue(Issue issues) throws Exception {
        if (!isIssueNameUnique(issues.getIssuename())) {
            throw new Exception("Issuename finns redan");
        }
        return mongoOperations.insert(issues);
    }

    public List<Issue> getIssues() {
        return mongoOperations.findAll(Issue.class);
    }

    public Issue getIssueById(UUID issueId) {
        Query query = new Query(Criteria.where("issueId").is(issueId));
        Issue existingIssue = mongoOperations.findOne(query, Issue.class);
        return existingIssue;
    }

    public Issue updateIssue(Issue updatedIssue) {
        return mongoOperations.save(updatedIssue);
    }

    public void deleteIssue(UUID issueId) {
        Query query = new Query(Criteria.where("issueId").is(issueId));
        mongoOperations.remove(query, Issue.class);

    }

    public Issue closeIssue(UUID id) {
        if (getIssueById(id) == null) {
            throw new IllegalArgumentException("Issue not found");
        }
        Issue issue = getIssueById(id);

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
