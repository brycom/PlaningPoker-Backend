package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Vote;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Services.VoteService;

@RestController
@RequestMapping("/votes")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @GetMapping
    public ResponseEntity<List<Vote>> getAllVotes() {
        List<Vote> votes = voteService.getAllVotes();
        return ResponseEntity.ok(votes);
    }

    @PostMapping
    public ResponseEntity<Vote> saveVote(@RequestBody Vote vote) {
        Vote savedVote = voteService.saveVote(vote);
        return ResponseEntity.ok(savedVote);
    }

}
