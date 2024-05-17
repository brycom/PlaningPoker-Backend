package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Services;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;


@Service
public class ProjectService {

    
    private MongoOperations mongoOperations;

    public ProjectService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

  
    
}
