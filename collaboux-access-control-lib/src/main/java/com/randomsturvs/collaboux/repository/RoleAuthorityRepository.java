package com.randomsturvs.collaboux.repository;

import com.randomsturvs.collaboux.entity.Role;
import com.randomsturvs.collaboux.entity.RoleAuthority;
import com.randomsturvs.collaboux.entity.UserRole;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.NamedNativeQuery;
import java.util.List;

public interface RoleAuthorityRepository extends BaseRepository<RoleAuthority, Long> {

    @Query(nativeQuery = true, value="truncate table role_authority")
    void truncate();

    List<RoleAuthority> findAllByRole(Role role);
}
