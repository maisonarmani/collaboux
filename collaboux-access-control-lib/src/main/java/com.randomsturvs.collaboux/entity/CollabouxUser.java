package com.randomsturvs.collaboux.entity;

import org.springframework.security.core.userdetails.User;

public class CollabouxUser extends User {

    private static final long serialVersionUID = 1L;

    public CollabouxUser(com.randomsturvs.collaboux.entity.User user) {
        super(user.getUsername(), user.getPassword(), user.getGrantedAuthorities());
    }
}
