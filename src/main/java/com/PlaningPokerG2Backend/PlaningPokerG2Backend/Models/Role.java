package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

@Document(collection = "Roles")
public class Role implements GrantedAuthority { 
    
    @Id
    private int id;
    private String authority;

    public Role(String authority) {
        this.authority = authority;
    }

    public Role(int id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public Role() {
        super();
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }


}
