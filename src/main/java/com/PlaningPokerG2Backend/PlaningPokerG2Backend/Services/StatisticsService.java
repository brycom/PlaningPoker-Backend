package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Issues;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Project;

// Statistiken beräknas per projekt,
// varje projekt har sina egna beräkningar för genomsnittliga röster per issue, 
// genomsnittlig röst för en specifik issue, 
// och issues med röster över ett visst tröskelvärde.
// det vill säga varje projekt har sina egna specifika uppgifter, användare och röster.

@Service
public class StatisticsService {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private IssuesService issuesService;

    // Beräkna genomsnittliga röster per issue för ett enskilt projekt
    public double getAverageVotesPerIssue(String projectId) {
        Project project = projectService.getProjectById(projectId);
        if (project != null) {
            return project.getAverageVotesPerIssue();
        }
        return 0;
    }

    // Beräkna genomsnittlig röst för en specifik issue inom ett projekt
    public double getAverageVoteForIssue(UUID issueId) {
        Issues issue = issuesService.getIssueById(issueId);
        if (issue != null) {
            return issue.getAverageVote();
        }
        return 0;
    }

    // Hämta issues med röster över ett visst tröskelvärde för ett enskilt projekt
    public List<Issues> getIssuesWithVotesAboveThreshold(String projectId, float threshold) {
        Project project = projectService.getProjectById(projectId);
        if (project != null) {
            return project.getIssues().stream()
                    .filter(issue -> issue.getVotes().stream().anyMatch(vote -> vote.getVote() > threshold))
                    .collect(Collectors.toList());
        }
        return null;
    }
}
