package com.randomsturvs.collaboux.entity;

import javax.persistence.*;


@Entity
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    public UserRole() {
    }
}
