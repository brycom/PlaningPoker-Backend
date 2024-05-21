package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Vote;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Repositories.CustomProjectRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    private CustomProjectRepository customProjectRepository;

    

    public VoteController(CustomProjectRepository customProjectRepository) {
        this.customProjectRepository = customProjectRepository;
    }



    @GetMapping("/votes/{projectId}/{issueId}")
    public List<Vote> getVotes(@PathVariable String projectId, @PathVariable String issueId) {

        List<Vote> votes = customProjectRepository.findVotesByIssueId(projectId, issueId);
        return votes;
    }

    // add vote to issue with userId

    // reset vote
    
}
