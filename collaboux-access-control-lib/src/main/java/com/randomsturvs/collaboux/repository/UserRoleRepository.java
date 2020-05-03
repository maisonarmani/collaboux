package com.randomsturvs.collaboux.repository;

import com.randomsturvs.collaboux.entity.User;
import com.randomsturvs.collaboux.entity.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserRoleRepository extends BaseRepository<UserRole, Long> {

    List<UserRole> findAllByUser(User user);
}
