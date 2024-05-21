package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;

import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Vote;

@Service
public class VoteService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Vote> getAllVotes() {
        return mongoTemplate.findAll(Vote.class);
    }

    public Vote saveVote(Vote vote) {
        return mongoTemplate.save(vote);
    }
    
}
