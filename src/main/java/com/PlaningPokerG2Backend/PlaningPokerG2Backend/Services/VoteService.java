package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Services;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Project;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Vote;

@Service
public class VoteService {
    
    private final MongoOperations mongoOperations;

    public VoteService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    // public Vote addUserVote(Vote vote, String projectId, String issueId ) {
    //     System.out.println("proj: " + projectId);
    //     System.out.println("issue: " + issueId);

    //     Query query = new Query(Criteria.where("id").is(projectId));
    //     Project project = mongoOperations.findOne(query, Project.class);
    //     System.out.println("project: " + project);

    // }
}
