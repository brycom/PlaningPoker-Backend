package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "Users")
public class User implements UserDetails {

    @Id
    private String userId;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private Set<Role> role;

    public User() {
        super();
        this.role = new HashSet<Role>();
    }

    public User(String username, String firstName, String lastName, String password, String email, Set<Role> role) {
        super();
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void addRole(Role role) {
        if (role != null) {
            this.role.add(role);
        } else {
            throw new IllegalArgumentException("Kan inte lägga till rollen");
        }
    }

    public void removeRole(Role role) {
        if (this.role.size() > 1) {
            this.role.remove(role);
        } else {
            throw new IllegalStateException("Kan inte ta bort rollen");
        }
    }

    public void switchRole(Role role) {
        Object[] roles = this.role.toArray();
        Role oldRole = (Role) roles[0];
        this.role.add(role);
        this.role.remove(oldRole);

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return this.role;
    }

    @Override
    public String getUsername() {

        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }
}
