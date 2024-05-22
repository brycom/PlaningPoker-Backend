package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Project;

@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {
    
}
