package com.randomsturvs.collaboux.repository;

import com.randomsturvs.collaboux.entity.Role;

import java.util.List;


public interface RoleRepository extends BaseRepository<Role, Long> {

    @Override
    List<Role> findAll();
}
