package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Services;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Project;

@Service
public class ProjectService {

    private final MongoOperations mongoOperations;

    public ProjectService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public Project getProjectById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));

        return mongoOperations.findOne(query, Project.class);
    }

    public List<Project> getProjects() {
        return mongoOperations.findAll(Project.class);
    }

    public Project createProjects(Project project) {
        return mongoOperations.insert(project);
    }

    public void deleteProject(String id) {
        Query query = Query.query(Criteria.where("id").is(id));

        mongoOperations.remove(query, Project.class);
    }

    public Project editProjects(String id, Project project) {
        Query query = Query.query(Criteria.where("id").is(id));
        Update update = Update.update("projectName", project.getProjectName());

        mongoOperations.updateFirst(query, update, Project.class);
        return mongoOperations.findById(id, Project.class);
    }

}
