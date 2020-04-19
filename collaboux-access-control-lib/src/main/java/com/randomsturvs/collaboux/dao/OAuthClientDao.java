package com.randomsturvs.collaboux.dao;


import com.randomsturvs.collaboux.entity.OauthClient;
import com.randomsturvs.collaboux.repository.OAuthClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class OAuthClientDao {

    @Autowired
    private OAuthClientRepository oAuthClientRepository;

    public OauthClient getClientDetails(String client_id) {
        OauthClient oauthClientEntities =  oAuthClientRepository.findDistinctByClientId(client_id);
        return oauthClientEntities;
    }
}
