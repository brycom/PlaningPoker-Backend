package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.DTOs.VoteStatisticDTO;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Services.VoteStatisticService;

@RestController
@RequestMapping("/statistics")
public class VoteStatisticController {

    @Autowired
    private VoteStatisticService voteStatisticService;

    @GetMapping
    public ResponseEntity<List<VoteStatisticDTO>> getStatistics() {
        List<VoteStatisticDTO> statistics = voteStatisticService.getVoteStatistics();
        return ResponseEntity.ok(statistics);
    }
    
}
