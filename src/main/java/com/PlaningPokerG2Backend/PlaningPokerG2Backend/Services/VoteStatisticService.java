package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Vote;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.DTOs.VoteStatisticDTO;

@Service
public class VoteStatisticService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<VoteStatisticDTO> getVoteStatistics() {
        List<Vote> votes = mongoTemplate.findAll(Vote.class);

        return votes.stream()
            .collect(Collectors.groupingBy(Vote::getVote, Collectors.counting()))
            .entrySet().stream()
            .map(e -> new VoteStatisticDTO(e.getKey(), e.getValue()))
            .collect(Collectors.toList());
    }
    
}
