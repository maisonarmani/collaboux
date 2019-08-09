package com.randomsturvs.excollabo.config.oauth2.entity;

import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;


@Entity
@Table(name="oauth_client")
public class OauthClientEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String clientId;

    @NotNull
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }


    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public void setRegisteredRedirectUri(String registeredRedirectUri) {
        this.registeredRedirectUri = registeredRedirectUri;
    }


    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public Boolean getScoped() {
        return isScoped;
    }

    public void setScoped(Boolean scoped) {
        isScoped = scoped;
    }

    public Boolean getSecretRequired() {
        return isSecretRequired;
    }

    public void setSecretRequired(Boolean secretRequired) {
        isSecretRequired = secretRequired;
    }

    public String getIsAutoApprove() {
        return isAutoApprove;
    }

    public void setIsAutoApprove(String isAutoApprove) {
        this.isAutoApprove = isAutoApprove;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }

    public Set<GrantedAuthority> getAuthorities() {
        List<String> authorities = new ArrayList(Arrays.asList(this.authorities.split(",")));
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities = authorities.stream().map(n-> new Authority(n)).collect(Collectors.toList());
        return new HashSet<>(grantedAuthorities) ;
    }

    public Set<String>  getRegisteredRedirectUri() {
        return toHashSet(registeredRedirectUri);
    }

    public  Set<String>  getResourceIds() {
        return toHashSet(resourceIds);
    }

    public Set<String> getAuthorizedGrantTypes() {
        return toHashSet(this.authorizedGrantTypes);
    }


    public Set<String> getScope() {
        return toHashSet(scope);
    }

    private Set<String> toHashSet(String param){
        List<String> initial = new ArrayList(Arrays.asList(param.split(",")));
        return new HashSet(initial);
    }


}