package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Issue;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Services.StatisticsService;

import java.util.List;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/averageVotes/{projectId}")
    public double getAverageVotes(@PathVariable String projectId) {
        return statisticsService.getAverageVotesPerIssue(projectId);
    }

    @GetMapping("/averageVoteForIssue/{issueId}")
    public double getAverageVoteForIssue(@PathVariable String issueId) {
        return statisticsService.getAverageVoteForIssue(issueId);
    }

    @GetMapping("/issuesWithHighVotes/{projectId}/{threshold}")
    public List<Issue> getIssuesWithHighVotes(@PathVariable String projectId, @PathVariable float threshold) {
        return statisticsService.getIssuesWithVotesAboveThreshold(projectId, threshold);
    }
}


// A. Hämta genomsnittligt antal röster per issue för ett projekt - {projectId}
// http://localhost:8080/statistics/averageVotes/12345

// B. Hämta genomsnittlig röst för en specifik issue - {issueId}
// Get http://localhost:8080/statistics/averageVoteForIssue/123e4567-e89b-12d3-a456-426614174000

// C. Hämta issues med röster över ett visst tröskelvärde för ett projekt -{projectId}/{threshold}
// http://localhost:8080/statistics/issuesWithHighVotes/12345/4.0



