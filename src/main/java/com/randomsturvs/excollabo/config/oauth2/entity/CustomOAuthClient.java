package com.randomsturvs.excollabo.config.oauth2.entity;

import com.randomsturvs.excollabo.config.oauth2.entity.OauthClientEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CustomOAuthClient implements ClientDetails {

    private static final long serialVersionUID = 1L;

    private String clientId;
    private String clientSecret;
    private Set<String> resourceIds;
    private Set<String> scope;
    private Set<String> authorizedGrantTypes;
    private Set<String> registeredRedirectUri;
    private Collection<GrantedAuthority> authorities;
    private Boolean isScoped;
    private Boolean isSecretRequired;
    private String isAutoApprove;
    private Map<String, Object> additionalInformation;
    private Integer accessTokenValiditySeconds;
    private Integer refreshTokenValiditySeconds;

    public CustomOAuthClient(OauthClientEntity oauthClientEntity){
        this.clientId= oauthClientEntity.getClientId();
        this.clientSecret= oauthClientEntity.getClientSecret();
        this.resourceIds= oauthClientEntity.getResourceIds();
        this.scope= oauthClientEntity.getScope();
        this.registeredRedirectUri= oauthClientEntity.getRegisteredRedirectUri();
        this.authorizedGrantTypes= oauthClientEntity.getAuthorizedGrantTypes();
        this.authorities= oauthClientEntity.getAuthorities();
        this.isScoped= oauthClientEntity.getScoped();
        this.isSecretRequired= oauthClientEntity.getSecretRequired();
        this.isAutoApprove = oauthClientEntity.getIsAutoApprove();
        this.additionalInformation = null;
        this.accessTokenValiditySeconds= oauthClientEntity.getAccessTokenValiditySeconds();
        this.refreshTokenValiditySeconds= oauthClientEntity.getRefreshTokenValiditySeconds();

    }
    @Override
    public String getClientId() {
        return clientId;
    }


    @Override
    public Set<String> getResourceIds() {
        return resourceIds;
    }



    @Override
    public Set<String> getScope() {
        return scope;
    }

    @Override
    public String getClientSecret() {
        return clientSecret;
    }


    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }



    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }


    @Override
    public Map<String, Object> getAdditionalInformation() {
        Map<String, Object> information = new HashMap<>();
        information.put("organization", "RandomSturv.inc");
        return information;
    }


    @Override
    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }


    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    @Override
    public boolean isSecretRequired() {
        return isSecretRequired;
    }

    @Override
    public boolean isScoped() {
        return isScoped;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return registeredRedirectUri;
    }

    @Override
    public boolean isAutoApprove(String s) {
        return false;
    }

    private Set<String> toList(String string){
        return null;
    }
}
