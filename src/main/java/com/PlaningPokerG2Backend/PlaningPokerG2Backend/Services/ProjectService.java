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

    public Project getProjectById(String projektId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("projektId").is(projektId));

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

    public void deleteProject(String projektId) {
        Query query = Query.query(Criteria.where("projektId").is(projektId));

        mongoOperations.remove(query, Project.class);
    }

    public Project editProjects(String projektId, Project project) {
        Query query = Query.query(Criteria.where("projektId").is(projektId));
        Update update = Update.update("projectname", project.getProjectname());

        mongoOperations.updateFirst(query, update, Project.class);
        return mongoOperations.findById(projektId, Project.class);
    }

    public Project addUserToProject(String projektId, String userId) {
        Query query = new Query(Criteria.where("ProjektId").is(projektId));
        Update update = new Update().addToSet("userIds", userId);
        mongoOperations.updateFirst(query, update, Project.class);

        return mongoOperations.findAndModify(query, update, Project.class);

        //Behöver kollas över när vi ändrar projectId och userId
    }

    public Project deleteUserFromProject(String projektId, String userId) {
        Query query = new Query(Criteria.where("projektId").is(projektId));
        Update update = new Update().pull("userIds", userId); // Tar bort användar-ID från listan
        mongoOperations.updateFirst(query, update, Project.class); // Uppdaterar projektet
        return mongoOperations.findById(projektId, Project.class); // Returnerar det uppdaterade projektet

        //Behöver kollas över när vi ändrar projectId och userId
    }

}
