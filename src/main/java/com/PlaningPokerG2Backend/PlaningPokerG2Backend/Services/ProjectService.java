package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Services;
import java.util.List;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Issues;

@Service
public class ProjectService {

    
    private MongoOperations mongoOperations;

    public ProjectService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public List<Issues> getIssues(){
        return mongoOperations.findAll(Issues.class);
    }
    
}
