package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Controllers;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
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
import com.PlaningPokerG2Backend.PlaningPokerG2Backend.Services.TokenService;

@RestController
@RequestMapping("/issue")
@CrossOrigin("*")
public class IssuesController {

    private IssuesService issuesService;
    private TokenService tokenService;

    public IssuesController(IssuesService issuesService, TokenService tokenService) {
        this.issuesService = issuesService;
        this.tokenService = tokenService;
    }

    @PostMapping("/{projectId}")
    public ResponseEntity<Object> addIssue(@AuthenticationPrincipal Jwt jwt, @PathVariable String projectId,
            @RequestBody Issue issue) {
        try {
            String user = tokenService.getUserFromToken(jwt);
            issue.setStartTime(LocalDateTime.now());
            if (issue.getEstimatedTime() == 0) {
                issue.setEstimatedTime(0.0f);
            }
            Issue newIssue = issuesService.addIssue(user, projectId, issue);
            return new ResponseEntity<>(newIssue, HttpStatus.CREATED);
        } catch (Exception ex) {
            String errorMessage = "Issuename finns redan!";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<List<Issue>> getIssues(@AuthenticationPrincipal Jwt jwt, @PathVariable String projectId) {
        try {
            String user = tokenService.getUserFromToken(jwt);
            List<Issue> issues = issuesService.getIssues(user, projectId);
            return new ResponseEntity<>(issues, HttpStatus.OK);
        } catch (Exception ex) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{projectId}/{issueId}")
    public ResponseEntity<?> updateIssue(@AuthenticationPrincipal Jwt jwt, @RequestBody Issue issues,
            @PathVariable String projectId, @PathVariable String issueId) {
        try {
            String user = tokenService.getUserFromToken(jwt);

            return ResponseEntity.ok(issuesService.updateIssue(user, projectId, issueId, issues));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{projectId}/{issueId}")
    ResponseEntity<?> deleteIssue(@AuthenticationPrincipal Jwt jwt, @PathVariable String projectId,
            @PathVariable String issueId) {
        try {
            String user = tokenService.getUserFromToken(jwt);
            issuesService.deleteIssue(user, projectId, issueId);
            return ResponseEntity.status(HttpStatus.OK).body("Issue borttagen");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Borttagning av issue misslyckades");
        }
    }

    @PutMapping("/{projectId}/{issueId}/close")
    ResponseEntity<String> closeIssue(@AuthenticationPrincipal Jwt jwt, @PathVariable String issueId,
            @PathVariable String projectId) {
        try {
            String user = tokenService.getUserFromToken(jwt);
            Issue closedIssue = issuesService.closeIssue(user, projectId, issueId);
            String actualTime = closedIssue.getActualTime();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(actualTime);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Stängning a issue misslyckades");
        }
    }

}
