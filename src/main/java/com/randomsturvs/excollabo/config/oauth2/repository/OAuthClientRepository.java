package com.randomsturvs.excollabo.config.oauth2.repository;

import com.randomsturvs.excollabo.config.BaseRepository;
import com.randomsturvs.excollabo.config.oauth2.entity.OauthClientEntity;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;



@Repository
public interface OAuthClientRepository extends BaseRepository<OauthClientEntity,Long> {
    OauthClientEntity findDistinctByClientId(String clientId);

    boolean existsByClientId(String clientId);
}
