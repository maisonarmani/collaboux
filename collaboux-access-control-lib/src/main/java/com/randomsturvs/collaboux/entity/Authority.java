package com.randomsturvs.collaboux.entity;


import com.randomsturvs.collaboux.enums.DomainEnum;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
public class Authority  implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    private String friendlyName;

    private String description;

    @Enumerated(EnumType.STRING)
    private DomainEnum domain;

    public Authority() {}

    public Authority(@NotNull String name) {
        this.name = name;
    }

    public Authority(@NotNull String name, String friendlyName, DomainEnum domain) {
        this.name = name;
        this.friendlyName = friendlyName;
        this.domain = domain;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public DomainEnum getDomain() {
        return domain;
    }

    public void setDomainEnum(DomainEnum domain) {
        this.domain = domain;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
