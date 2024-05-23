package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;

public class Issue {
    @Id

    private String issueId;
    private String issuename;
    private List<Vote> votes;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private float estimatedTime;
    private String actualTime;

    //Lista för votes görs i konstrukton istället för input
    public Issue(String issuename, LocalDateTime startTime, LocalDateTime endTime,
            float estimatedTime, String actualTime) {

        this.issueId = UUID.randomUUID().toString();
        this.issuename = issuename;
        this.votes = new ArrayList<Vote>();
        this.startTime = startTime;
        this.endTime = endTime;
        this.estimatedTime = estimatedTime;
        this.actualTime = actualTime;
    }

    public String getIssuename() {
        return issuename;
    }

    public void setIssuename(String issuename) {
        this.issuename = issuename;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public float getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(float estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getActualTime() {
        return actualTime;
    }

    public void setActualTime(String actualTime) {
        this.actualTime = actualTime;
    }

    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

}
