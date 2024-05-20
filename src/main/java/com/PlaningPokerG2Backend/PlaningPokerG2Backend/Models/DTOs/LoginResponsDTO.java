package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models.DTOs;

public class LoginResponsDTO {

    private UserDTO user;
    private String token;

    public LoginResponsDTO() {
        super();
    }

    public LoginResponsDTO(UserDTO user, String token) {
        this.user = user;
        this.token = token;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
