package com.randomsturvs.collaboux.repository;

import com.randomsturvs.collaboux.entity.Authority;
import com.randomsturvs.collaboux.entity.Role;
import org.springframework.data.jpa.repository.Query;

public interface AuthorityRepository extends BaseRepository<Authority, Long> {

    @Query("SELECT id FROM Authority WHERE name=:name")
    Long getIdWithName(String name);
}
