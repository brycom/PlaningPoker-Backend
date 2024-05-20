package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.DTOs;

public class UserDTO {
    private String username;
    private String firstname;
    private String userId;

    public UserDTO(String username, String firstname, String userId) {
        this.username = username;
        this.firstname = firstname;
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
