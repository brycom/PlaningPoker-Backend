package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Vote {
    @Id
    private UUID voteId;
    private String userId;
    private float vote;

    public Vote(String userId, float vote) {
        this.voteId = UUID.randomUUID();
        this.userId = userId;
        this.vote = vote;
    }

    public UUID getVoteId() {
        return voteId;
    }

    public void setVoteId(UUID voteId) {
        this.voteId = voteId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public float getVote() {
        return vote;
    }

    public void setVote(float vote) {
        this.vote = vote;
    }
}
