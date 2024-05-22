package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Issue;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Project;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Vote;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Services.ProjectService;

import java.util.List;

@Repository
public class CustomProjectRepository {

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private ProjectService projectService;

    public List<Vote> findVotesByIssueId(String projectId, String issueId) {
        System.out.println("Project ID: " + projectId);
        System.out.println("Issue ID: " + issueId);

        Query query = new Query(Criteria.where("id").is(projectId));

        System.out.println("query: " + query);

        Project project = mongoOperations.findOne(query, Project.class);

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

        Query query = new Query(Criteria.where("id").is(projectId));
        Project project = mongoOperations.findOne(query, Project.class);

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

    // public String resetVotes(String projectId, String issueId) {

    //     Query query = new Query(Criteria.where("id").is(projectId));
    //     Project project = mongoOperations.findOne(query, Project.class);

    // }
}