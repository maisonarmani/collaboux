package com.randomsturvs.collaboux.repository;

import com.randomsturvs.collaboux.entity.OauthClient;
import com.randomsturvs.collaboux.repository.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQuery;

public interface OAuthClientRepository extends JpaRepository<OauthClient,Long> {
    OauthClient findDistinctByClientId(String clientId);

    @Query("SELECT id from OauthClient where clientId = :clientId")
    Long findIdByClientId(String clientId);

}


