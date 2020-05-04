package com.randomsturvs.collaboux.models;

import com.randomsturvs.collaboux.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserResponse {

    private String username;

    private String email;

    private String fullName;

    private List<String> authorities;

    private List<String> roles;

    public UserResponse(User user) {
        this.username =  user.getUsername();
        this.email =  user.getEmail();
        this.roles =  user.getRoles().stream().map(role->role.getName()).collect(Collectors.toList());
        this.authorities = user.getGrantedAuthorities().stream().map(a->a.getAuthority()).collect(Collectors.toList());
        this.fullName =  user.getName();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
