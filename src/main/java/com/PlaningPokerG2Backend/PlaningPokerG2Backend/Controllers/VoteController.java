package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Vote;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Repositories.CustomProjectRepository;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Services.VoteService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    private CustomProjectRepository customProjectRepository;

    private VoteService voteService;

    public VoteController(CustomProjectRepository customProjectRepository, VoteService voteService) {
        this.customProjectRepository = customProjectRepository;
        this.voteService = voteService;
    }

    @GetMapping("/votes/{projectId}/{issueId}")
    public List<Vote> getVotes(@PathVariable String projectId, @PathVariable String issueId) {

        List<Vote> votes = voteService.findVotesByIssueId(projectId, issueId);
        return votes;
    }

    @PostMapping("/uservote/{projectId}/{issueId}")
    public Vote addUserVote(@RequestBody Vote vote, @PathVariable String projectId, @PathVariable String issueId) {
        voteService.addUserVote(vote, projectId, issueId);
        return vote;
    }

    @DeleteMapping("/deletevotes/{projectId}/{issueId}")
    public String resetVotes(@PathVariable String projectId, @PathVariable String issueId) {

        return voteService.resetVotes(projectId, issueId);
    }

    @GetMapping("/uservote/{projectId}/{issueId}/{userId}")
    public Vote getUserVote(@PathVariable String projectId, @PathVariable String issueId, @PathVariable String userId) {
        return voteService.getUserVote(projectId, issueId, userId);
    }

}
