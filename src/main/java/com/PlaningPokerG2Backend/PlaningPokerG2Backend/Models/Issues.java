package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;

public class Issues {
    @Id

    private String id;
    private String issuename;
    private List <Vote> votes;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private float estimatedTime;
    private String actualTime;

    public Issues(String issuename, List<Vote> votes, LocalDateTime startTime, LocalDateTime endTime,
            float estimatedTime, String actualTime) {

        this.id = UUID.randomUUID().toString();
        this.issuename = issuename;

        this.votes = votes;
        this.startTime = startTime;
        this.endTime = endTime;
        this.estimatedTime = estimatedTime;
        this.actualTime = actualTime;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;

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

}
