package com.randomsturvs.collaboux.services;

import com.randomsturvs.collaboux.dao.OAuthClientDao;
import com.randomsturvs.collaboux.entity.OauthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

@Service
public class CollabouxClientDetailsService implements ClientDetailsService {
    @Autowired
    OAuthClientDao oAuthClientDao;

    @Override
    public ClientDetails loadClientByClientId(String client) throws ClientRegistrationException {
        OauthClient oauthClient = null;
        try {
            oauthClient = oAuthClientDao.getClientDetails(client);
            return  OauthClient.getClientDetailsFromEntity(oauthClient);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("Client " + client + " was not found in the database");
        }
    }

}