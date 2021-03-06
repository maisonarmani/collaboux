package com.randomsturvs.collaboux.repository;

import com.randomsturvs.collaboux.entity.User;
import com.randomsturvs.collaboux.entity.UserRole;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends BaseRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query("SELECT id FROM User  WHERE email = :email")
    Long existsByEmail(String email);

}
