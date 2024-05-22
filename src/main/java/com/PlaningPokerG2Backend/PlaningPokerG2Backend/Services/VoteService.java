package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Services;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Issues;
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

        List<Issues> allIssues = project.getIssues();
        System.out.println("allIssues" + allIssues);

        for (Issues issue : allIssues) {
            if (issue.getId().equals(issueId)) {

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
    
        List<Issues> allIssues = project.getIssues();
        System.out.println("allIssues: " + allIssues);
       
        for (Issues issue : allIssues) {
            if (issue.getId().equals(issueId)) {          
                issue.getVotes().add(vote);      
                mongoOperations.save(project);
                return vote;
            }
        }
    
        System.out.println("Issue not found");
        return null;
    }

    public String resetVotes(String projectId, String issueId) {
        // Retrieve the project using the provided projectId
        Project project = mongoOperations.findById(projectId, Project.class);
    
        // Check if the project exists
        if (project == null) {
            System.out.println("Project not found");
            return "Project not found";
        }
    
        // Get the list of all issues in the project
        List<Issues> allIssues = project.getIssues();
        System.out.println("allIssues: " + allIssues);
    
        // Iterate over the list of issues to find the specific issue
        for (Issues issue : allIssues) {
            if (issue.getId().equals(issueId)) {
                // Clear all votes for the found issue
                issue.getVotes().clear();
    
                // Save the updated project back to the database
                mongoOperations.save(project);
    
                // Return a success message
                return "Votes reset successfully";
            }
        }
    
        // If the issue was not found, return an appropriate message
        return "Issue not found";
    }
    
}
