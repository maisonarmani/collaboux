package com.randomsturvs.excollabo.config.excollabo.service;

import com.randomsturvs.excollabo.config.oauth2.entity.CustomOAuthClient;
import com.randomsturvs.excollabo.config.oauth2.entity.OauthClientEntity;
import com.randomsturvs.excollabo.config.oauth2.repository.OAuthClientRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ExcollaboServiceRegistration implements InitializingBean {

    @Value("${app.client.id:excollabo}")
    private String clientId;

    @Value("${app.client.secret:secret}")
    private String clientSecret;

    private static final String SCOPE = "profile";

    @Value("${excollabo.oauth.client.resource_ids:excollabo-service}")
    private String resourceIds;

    @Value("${excollabo.oauth.client.redirect_uri:http://localhost:8080}")
    private String webServerRedirectUris;

    @Autowired
    OAuthClientRepository oAuthClientRepository;

    @Qualifier("passwordEncoder")
    @Autowired
    PasswordEncoder passwordEncoder;


    private static final String AUTO_APPROVED_SCOPES = SCOPE;

    private static final String AUTHORIZED_GRANT_TYPES = "implicit,client_credentials,password,authorization_code";

    private static final String AUTHORITIES = "ROLE_CLIENT,CS_CREATE_AND_APPROVE_USER,VIEW_USER_DETAILS";

    private static final Integer ACCESS_TOKEN_VALIDITY_SECONDS = 360000000;

    private static final Integer REFRESH_TOKEN_VALIDITY_SECONDS = 360000000;


    public OauthClientEntity buildOauthClientDetails() {
        OauthClientEntity oauthClientDetails = new OauthClientEntity();

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
        //
        OauthClientEntity oauthClientEntity = buildOauthClientDetails();
        CustomOAuthClient customOAuthClient = new CustomOAuthClient(oauthClientEntity);

        // Please make sure to validate this
        if (oAuthClientRepository.existsByClientId(oauthClientEntity.getClientId())){
            oauthClientEntity.setId(oAuthClientRepository.findDistinctByClientId(oauthClientEntity.getClientId()).getId());
           oAuthClientRepository.save(oauthClientEntity);
        }
        oAuthClientRepository.save(oauthClientEntity);
    }
}



