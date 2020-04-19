package com.randomsturvs.collaboux.entity;


import com.randomsturvs.collaboux.enums.Domain;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
public class Authority  implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column(unique = true)
    private String name;

    private String friendlyName;

    private String description;

    @Enumerated(EnumType.STRING)
    private Domain domain;

    public Authority(@NotNull String name) {
        this.name = name;
    }

    public Authority(@NotNull String name, String friendlyName, Domain domain) {
        this.name = name;
        this.friendlyName = friendlyName;
        this.domain = domain;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
