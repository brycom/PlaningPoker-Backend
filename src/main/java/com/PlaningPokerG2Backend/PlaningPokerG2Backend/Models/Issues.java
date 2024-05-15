package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;

public class Issues {
    @Id
    private UUID id;
    private String issueName;
    private List <Vote> votes;
    private LocalDate startTime;
    private LocalDate endTime;
    private float estimatedTime;
    private float actualTime;

    public Issues(String issueName, List<Vote> votes, LocalDate startTime, LocalDate endTime,
            float estimatedTime, float actualTime) {
        this.id = UUID.randomUUID();
        this.issueName = issueName;
        this.votes = votes;
        this.startTime = startTime;
        this.endTime = endTime;
        this.estimatedTime = estimatedTime;
        this.actualTime = actualTime;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getIssueName() {
        return issueName;
    }
    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }
    public List<Vote> getVotes() {
        return votes;
    }
    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
    public LocalDate getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }
    public LocalDate getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }
    public float getEstimatedTime() {
        return estimatedTime;
    }
    public void setEstimatedTime(float estimatedTime) {
        this.estimatedTime = estimatedTime;
    }
    public float getActualTime() {
        return actualTime;
    }
    public void setActualTime(float actualTime) {
        this.actualTime = actualTime;
    }

    


}
