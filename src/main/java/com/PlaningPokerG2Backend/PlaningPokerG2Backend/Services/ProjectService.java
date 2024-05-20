package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Services;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

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

        //Behöver kollas över när vi ändrar projectId och userId
        //denna ska endast lista de projekt som finns under en user
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

    public Project addUserToProject(String projectId, String userId) {
        Query query = new Query(Criteria.where("ProjectId").is(projectId));
        Update update = new Update().addToSet("userIds", userId);
        mongoOperations.updateFirst(query, update, Project.class); 

        return mongoOperations.findAndModify(query, update, Project.class);

        //Behöver kollas över när vi ändrar projectId och userId
    }

    public Project deleteUserFromProject(String projectId, String userId) {
        Query query = new Query(Criteria.where("id").is(projectId));
        Update update = new Update().pull("userIds", userId);  // Tar bort användar-ID från listan
        mongoOperations.updateFirst(query, update, Project.class);  // Uppdaterar projektet
        return mongoOperations.findById(projectId, Project.class);  // Returnerar det uppdaterade projektet

        //Behöver kollas över när vi ändrar projectId och userId
    }

    

}
