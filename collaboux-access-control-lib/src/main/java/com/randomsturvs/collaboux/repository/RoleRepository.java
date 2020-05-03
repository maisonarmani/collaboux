package com.randomsturvs.collaboux.repository;

import com.randomsturvs.collaboux.entity.Role;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface RoleRepository extends BaseRepository<Role, Long> {

    @Override
    List<Role> findAll();

    @Query("SELECT id FROM Role WHERE name=:name")
    Long getIdWithName(String name);

}
