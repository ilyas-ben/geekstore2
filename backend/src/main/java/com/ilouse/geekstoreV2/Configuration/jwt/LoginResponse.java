package com.ilouse.geekstoreV2.Configuration.jwt;

import java.util.List;

public class LoginResponse {
    private String username;
    private List<String> roles;
    private String jwt;

    public LoginResponse(String username, List<String> roles, String jwt) {
        this.username = username;
        this.roles = roles;
        this.jwt = jwt;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}

