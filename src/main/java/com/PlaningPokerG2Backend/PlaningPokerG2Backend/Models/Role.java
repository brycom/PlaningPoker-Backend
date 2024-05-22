package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

@Document(collection = "Roles")
public class Role implements GrantedAuthority {

    @Id
    private String roleId;
    private String authority;

    public Role(String authority) {
        this.authority = authority;
    }

    public Role() {
        super();

    }

    public String getRoleId() {
        return roleId;
    }

    public void setId(String roleId) {
        this.roleId = roleId;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

}
