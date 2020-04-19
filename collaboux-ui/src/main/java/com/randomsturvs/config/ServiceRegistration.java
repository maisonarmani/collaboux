package com.randomsturvs.config;

import com.randomsturvs.collaboux.entity.OauthClient;
import com.randomsturvs.collaboux.repository.OAuthClientRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ServiceRegistration implements InitializingBean {

    @Value("${app.client.id:collaboux}")
    private String clientId;

    @Value("${app.client.secret:collaboux}")
    private String clientSecret;

    private static final String SCOPE = "profile";

    @Value("${collaboux.oauth.client.resource_ids:collaboux}")
    private String resourceIds;

    @Value("${collaboux.oauth.client.redirect_uri:http://localhost:8080}")
    private String webServerRedirectUris;

    @Autowired
    OAuthClientRepository oAuthClientRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    private static final String AUTO_APPROVED_SCOPES = SCOPE;

    private static final String AUTHORIZED_GRANT_TYPES = "implicit,client_credentials,password,authorization_code";

    private static final String AUTHORITIES = "ROLE_CLIENT,CS_CREATE_AND_APPROVE_USER,VIEW_USER_DETAILS";

    private static final Integer ACCESS_TOKEN_VALIDITY_SECONDS = 360000000;

    private static final Integer REFRESH_TOKEN_VALIDITY_SECONDS = 360000000;


    public OauthClient buildOauthClientDetails() {
        OauthClient oauthClientDetails = new OauthClient();

        oauthClientDetails.setClientId(clientId);
        oauthClientDetails.setClientSecret(passwordEncoder.encode(clientSecret));
        oauthClientDetails.setScope(SCOPE);
        oauthClientDetails.setResourceIds(resourceIds);
        oauthClientDetails.setRegisteredRedirectUri(webServerRedirectUris);
        oauthClientDetails.setIsAutoApprove(AUTO_APPROVED_SCOPES);
        oauthClientDetails.setAuthorizedGrantTypes(AUTHORIZED_GRANT_TYPES);
        oauthClientDetails.setAuthorities(AUTHORITIES);
        oauthClientDetails.setAccessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS);
        oauthClientDetails.setRefreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS);

        return oauthClientDetails;
    }

    public String getClientIDSecret(){
        return clientId + ":" + clientSecret;
    }

    @Override
    public void afterPropertiesSet() {
        OauthClient oauthClientEntity = buildOauthClientDetails();

        if (oAuthClientRepository.existsByClientId(oauthClientEntity.getClientId())){
            oauthClientEntity.setId(oAuthClientRepository.findDistinctByClientId(oauthClientEntity.getClientId()).getId());
           oAuthClientRepository.save(oauthClientEntity);
        }
        oAuthClientRepository.save(oauthClientEntity);
    }
}



