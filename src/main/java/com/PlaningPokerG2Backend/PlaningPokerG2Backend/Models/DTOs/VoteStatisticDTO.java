package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.DTOs;

public class VoteStatisticDTO {
    private float vote;
    private long count;

    public VoteStatisticDTO(float vote, long count) {
        this.vote = vote;
        this.count = count;
    }

    public float getVote() {
        return vote;
    }

    public void setVote(float vote) {
        this.vote = vote;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
