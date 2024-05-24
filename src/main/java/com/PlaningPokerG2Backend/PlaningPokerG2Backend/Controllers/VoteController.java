package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Vote;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Services.VoteService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/vote")
public class VoteController {

    private VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping("/votes/{projectId}/{issueId}")
    public List<Vote> getVotes(@PathVariable String projectId, @PathVariable String issueId) {

        List<Vote> votes = voteService.findVotesByIssueId(projectId, issueId);
        return votes;
    }

    @PostMapping("/uservote/{projectId}/{issueId}")
    public List<Vote> addUserVotes(@RequestBody List<Vote> votes, @PathVariable String projectId,
            @PathVariable String issueId) {
        System.out.println("Received votes: " + votes + " for project: " + projectId + " and issue: " + issueId);

        for (Vote vote : votes) {
            voteService.addUserVote(vote, projectId, issueId);
        }

        return votes;
    }

    @DeleteMapping("/deletevotes/{projectId}/{issueId}")
    public String resetVotes(@PathVariable String projectId, @PathVariable String issueId) {

        return voteService.resetVotes(projectId, issueId);
    }

    @GetMapping("/uservote/{projectId}/{issueId}/{userId}")
    public Vote getUserVote(@PathVariable String projectId, @PathVariable String issueId, @PathVariable String userId) {
        return voteService.getUserVote(projectId, issueId, userId);
    }

    @GetMapping("/averagevote/{projectId}/{issueId}")
    public Double getAverageVote(@PathVariable String projectId, @PathVariable String issueId) {

        Double averageVote = voteService.getAverageVote(projectId, issueId);
        return averageVote;
    }
}
