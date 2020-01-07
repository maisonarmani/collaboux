package com.randomsturvs.collaboux.repository;

import com.randomsturvs.collaboux.entity.OauthClient;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuthClientRepository extends BaseRepository<OauthClient,Long>{
    OauthClient findDistinctByClientId(String clientId);

    boolean existsByClientId(String clientId);
}
