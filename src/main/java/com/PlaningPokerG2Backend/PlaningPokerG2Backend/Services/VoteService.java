package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Services;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Issue;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Project;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Vote;

@Service
public class VoteService {

    private final MongoOperations mongoOperations;

    public VoteService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public List<Vote> findVotesByIssueId(String projectId, String issueId) {
        System.out.println("Project ID: " + projectId);
        System.out.println("Issue ID: " + issueId);

        Project project = mongoOperations.findById(projectId, Project.class);

        if (project == null) {
            System.out.println("Project not found");
            return null;
        }

        List<Issue> allIssues = project.getIssues();
        System.out.println("allIssues" + allIssues);

        for (Issue issue : allIssues) {
            if (issue.getIssueId().equals(issueId)) {

                return issue.getVotes();
            }
        }

        System.out.println("Issue not found");
        return null;
    }

    public Vote addUserVote(Vote vote, String projectId, String issueId) {

        Project project = mongoOperations.findById(projectId, Project.class);

        if (project == null) {
            System.out.println("Project not found");
            return null;
        }

        List<Issue> allIssues = project.getIssues();
        System.out.println("allIssues: " + allIssues);

        for (Issue issue : allIssues) {
            if (issue.getIssueId().equals(issueId)) {
                issue.getVotes().add(vote);
                mongoOperations.save(project);
                return vote;
            }
        }

        System.out.println("Issue not found");
        return null;
    }

    public String resetVotes(String projectId, String issueId) {

        Project project = mongoOperations.findById(projectId, Project.class);

        if (project == null) {
            System.out.println("Project not found");
            return "Project not found";
        }

        List<Issue> allIssues = project.getIssues();
        System.out.println("allIssues: " + allIssues);

        for (Issue issue : allIssues) {
            if (issue.getIssueId().equals(issueId)) {

                issue.getVotes().clear();

                mongoOperations.save(project);

                return "Votes reset successfully";
            }
        }
        return "Issue not found";
    }

    public Vote getUserVote(String projectId, String issueId, String userId) {
        Project project = mongoOperations.findById(projectId, Project.class);

        if (project == null) {
            System.out.println("Project not found");
            return null;
        }
        List<Issue> allIssues = project.getIssues();
        for (Issue issue : allIssues) {
            if (issue.getIssueId().equals(issueId)) {

                List<Vote> votes = issue.getVotes();

                for (Vote vote : votes) {
                    if (vote.getUserId().equals(userId)) {
                        return vote;
                    }
                }
                System.out.println("Vote not found for userId: " + userId);
                return null;
            }
        }
        System.out.println("Issue not found");
        return null;
    }
}
