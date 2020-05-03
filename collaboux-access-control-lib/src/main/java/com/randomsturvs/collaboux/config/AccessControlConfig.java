package com.randomsturvs.collaboux.config;

import com.randomsturvs.collaboux.entity.Authority;
import com.randomsturvs.collaboux.entity.Role;
import com.randomsturvs.collaboux.entity.RoleAuthority;

import java.util.List;
import java.util.Set;

public class AccessControlConfig {

    private String name;
    private UpdateMode updateMode;
    private Set<Authority> authorities;
    private Set<Role> roles;
    private Set<RoleAuthority> authorityRoles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UpdateMode getUpdateMode() {
        return updateMode;
    }

    public void setUpdateMode(UpdateMode updateMode) {
        this.updateMode = updateMode;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<RoleAuthority> getAuthorityRoles() {
        return authorityRoles;
    }

    public void setAuthorityRoles(Set<RoleAuthority> authorityRoles) {
        this.authorityRoles = authorityRoles;
    }

    public enum UpdateMode{
        DELETE,UPDATE
    }

}
