package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.Issue;
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Services.IssuesService;

@RestController
@RequestMapping("/issue")
@CrossOrigin("*")
public class IssuesController {

    private IssuesService issuesService;

    @Autowired
    public IssuesController(IssuesService issuesService) {
        this.issuesService = issuesService;
    }

    @PostMapping("/")
    public ResponseEntity<Object> addIssue(@RequestBody Issue issue) {
        try {
            issue.setStartTime(LocalDateTime.now());
            if (issue.getEstimatedTime() == 0) {
                issue.setEstimatedTime(0.0f);
            }
            Issue newIssue = issuesService.addIssue(issue);
            return new ResponseEntity<>(newIssue, HttpStatus.CREATED);
        } catch (Exception ex) {
            String errorMessage = "Issuename finns redan!";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/issues")
    public ResponseEntity<List<Issue>> getIssues() {
        List<Issue> issues = issuesService.getIssues();
        return new ResponseEntity<>(issues, HttpStatus.OK);
    }

    @PatchMapping("/{issueId}")
    ResponseEntity<?> updateIssue(@RequestBody Issue issues, @PathVariable UUID issueId) {
        try {

            Issue existingIssue = issuesService.getIssueById(issueId);
            if (existingIssue == null) {
                return ResponseEntity.notFound().build();
            }
            if (issues.getIssuename() != null) {
                existingIssue.setIssuename(issues.getIssuename());
            }
            Issue updatedIssue = issuesService.updateIssue(existingIssue);
            return ResponseEntity.ok(updatedIssue);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Uppdatering av issue misslyckades");
        }

    }

    @DeleteMapping("/{issueId}")
    ResponseEntity<?> deleteIssue(@PathVariable UUID issueId) {
        issuesService.deleteIssue(issueId);
        return ResponseEntity.status(HttpStatus.OK).body("Issue borttagen");
    }

    @PutMapping("/{issueId}/close")
    ResponseEntity<String> closeIssue(@PathVariable UUID issueId) {
        try {
            Issue closedIssue = issuesService.closeIssue(issueId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Issue med namn:" + closedIssue.getIssuename() + " stängd.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Stängning a issue misslyckades");
        }
    }

}
