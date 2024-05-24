package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Services;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Project;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.User;

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

    public List<Project> getProjects(String user) {
        List<Project> projects = mongoOperations.findAll(Project.class);
        List<Project> usersProjects = new ArrayList<>();
        for (Project project : projects) {
            if (project.getUserIds().contains(user)) {
                usersProjects.add(project);

            }
        }
        return usersProjects;

        //Behöver kollas över när vi ändrar projectId och userId
        //denna ska endast lista de projekt som finns under en user
    }

    public Project createProjects(Project project, String user) {
        project.addUserIds(user);
        return mongoOperations.insert(project);
    }

    public void deleteProject(String user, String projektId) {
        Project project = mongoOperations.findById(projektId, Project.class);
        if (!project.getUserIds().contains(user)) {
            throw new IllegalArgumentException("Du har inte tillgång till detta projekt");
        }
        Query query = Query.query(Criteria.where("projektId").is(projektId));

        mongoOperations.remove(query, Project.class);
    }

    public Project editProjects(String user, String projektId, Project newProject) {
        Project project = mongoOperations.findById(projektId, Project.class);
        if (!project.getUserIds().contains(user)) {
            throw new IllegalArgumentException("Du har inte tillgång till detta projekt");
        }
        Query query = Query.query(Criteria.where("projektId").is(projektId));
        Update update = Update.update("projectname", newProject.getProjectname());

        mongoOperations.updateFirst(query, update, Project.class);
        return mongoOperations.findById(projektId, Project.class);
    }

    public Project addUserToProject(String user, String projektId, String userId) {
        Project project = mongoOperations.findById(projektId, Project.class);
        if (!project.getUserIds().contains(user)) {
            throw new IllegalArgumentException("Du har inte tillgång till detta projekt");
        }
        Query query = new Query(Criteria.where("ProjektId").is(projektId));
        Update update = new Update().addToSet("userIds", userId);
        mongoOperations.updateFirst(query, update, Project.class);

        return mongoOperations.findAndModify(query, update, Project.class);

        //Behöver kollas över när vi ändrar projectId och userId
    }

    public Project deleteUserFromProject(String user, String projektId, String userId) {
        Project project = mongoOperations.findById(projektId, Project.class);
        if (!project.getUserIds().contains(user)) {
            throw new IllegalArgumentException("Du har inte tillgång till detta projekt");
        }
        Query query = new Query(Criteria.where("projektId").is(projektId));
        Update update = new Update().pull("userIds", userId); // Tar bort användar-ID från listan
        mongoOperations.updateFirst(query, update, Project.class); // Uppdaterar projektet
        return mongoOperations.findById(projektId, Project.class); // Returnerar det uppdaterade projektet

        //Behöver kollas över när vi ändrar projectId och userId
    }

    public List<User> getUsersInProject(String user, String projektId) {
        Query query = new Query(Criteria.where("projektId").is(projektId));
        Project project = mongoOperations.findOne(query, Project.class);
        if (!project.getUserIds().contains(user)) {
            throw new IllegalArgumentException("Du har inte tillgång till detta projekt");
        }
        if (project != null && project.getUserIds() != null) {
            Query userQuery = new Query(Criteria.where("userId").in(project.getUserIds()));
            return mongoOperations.find(userQuery, User.class);
        }
        return new ArrayList<>();
    }

}
