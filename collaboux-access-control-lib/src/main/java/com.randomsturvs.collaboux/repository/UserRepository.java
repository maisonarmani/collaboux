package com.randomsturvs.collaboux.repository;

import com.randomsturvs.collaboux.entity.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
