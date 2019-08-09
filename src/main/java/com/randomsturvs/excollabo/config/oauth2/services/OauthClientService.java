package com.randomsturvs.excollabo.config.oauth2.services;

import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.stereotype.Service;


@Service
public class OauthClientService {

    private String clientSecret;
    private String resourceIds;
    private String scope;
    private String authorizedGrantTypes;
    private String registeredRedirectUri;
    private String authorities;
    private Boolean isScoped;
    private Boolean isSecretRequired;
    private String  isAutoApprove;
    private String  additionalInformation;
    private Integer accessTokenValiditySeconds;
    private Integer refreshTokenValiditySeconds;



    public String[] getScope() {
        return this.scope.split(",");
    }

    public String[] additionalInformation() {
        JSONParser jsonParser = new JSONParser(this.additionalInformation, null, true);
        Object parsed = jsonParser.parse();
        return null;

    }


}