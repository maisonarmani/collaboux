package com.randomsturvs.excollabo.config.oauth2.services;

import com.randomsturvs.excollabo.config.oauth2.dao.OAuthClientDao;
import com.randomsturvs.excollabo.config.oauth2.entity.CustomOAuthClient;
import com.randomsturvs.excollabo.config.oauth2.entity.OauthClientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

@Service
public class CustomClientDetailsService implements ClientDetailsService {
    @Autowired
    OAuthClientDao oAuthClientDao;

    @Override
    public ClientDetails loadClientByClientId(String client) throws ClientRegistrationException {
        OauthClientEntity oauthClientEntity = null;
        try {
            oauthClientEntity = oAuthClientDao.getClientDetails(client);
            CustomOAuthClient customOAuthClient = new CustomOAuthClient(oauthClientEntity);
            return customOAuthClient;
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("Client " + client + " was not found in the database");
        }
    }

}