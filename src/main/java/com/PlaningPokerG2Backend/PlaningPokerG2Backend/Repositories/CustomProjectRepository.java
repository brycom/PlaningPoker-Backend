package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Project;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Vote;


import java.util.List;


@Repository
public class CustomProjectRepository {

    @Autowired
    private MongoOperations mongoOperations;

    public CustomProjectRepository(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public List<Vote> findVotesByIssueId(String projectId, String issueId) {
        System.out.println("proj: " + projectId);
        System.out.println("issue: " + issueId);

        Query query = new Query(Criteria.where("id").is(projectId));

        Project project = mongoOperations.findOne(query, Project.class);
        System.out.println("project: " + project);
        if (project != null && project.getIssues() != null && !project.getIssues().isEmpty()) {
            return project.getIssues().get(0).getVotes();
        }
        System.out.println("Null!");
        return null;
    }

    public Project getProjectById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));

        return mongoOperations.findOne(query, Project.class);
    }

}