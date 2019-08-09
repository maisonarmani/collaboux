package com.randomsturvs.excollabo.config.oauth2.dao;


import com.randomsturvs.excollabo.config.oauth2.entity.OauthClientEntity;
import com.randomsturvs.excollabo.config.oauth2.repository.OAuthClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OAuthClientDao {

    @Autowired
    private OAuthClientRepository oAuthClientRepository;

    public OauthClientEntity getClientDetails(String client_id) {
        OauthClientEntity oauthClientEntities =  oAuthClientRepository.findDistinctByClientId(client_id);
        return oauthClientEntities;
    }
}
