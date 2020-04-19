package com.randomsturvs.collaboux.repository;

import com.randomsturvs.collaboux.entity.RoleAuthority;

import java.util.List;

public interface RoleAuthorityRepository extends BaseRepository<RoleAuthority, Long> {

    List<RoleAuthority> findAllByAuthorityEqualsOrderByRole(Long authorityId);
}
