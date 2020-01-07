package com.randomsturvs.collaboux.entity;

import com.randomsturvs.collaboux.model.Authority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;


@Entity
@Table(name="oauth_client")
public class OauthClient implements ClientDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotNull
    private String clientId;

    @NotNull
    private String clientSecret;
    private String resourceIds;
    private String scope;
    private String authorizedGrantTypes;
    private String registeredRedirectUri;
    private String authorities;
    private Integer accessTokenValiditySeconds;
    private Integer refreshTokenValiditySeconds;

    private boolean isScoped;
    private boolean isSecretRequired;
    private boolean isAutoApprove;

    @Transient
    private Map<String, Object>  additionalInformation;

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

    public boolean isScoped() {
        return isScoped;
    }

    public void setScoped(boolean scoped) {
        isScoped = scoped;
    }

    public boolean isSecretRequired() {
        return isSecretRequired;
    }

    public void setSecretRequired(Boolean secretRequired) {
        isSecretRequired = secretRequired;
    }

    public boolean isAutoApprove() {
        return isAutoApprove;
    }

    public void setIsAutoApprove(String isAutoApprove) {
        this.isAutoApprove = this.isAutoApprove(isAutoApprove);
    }

    public boolean isAutoApprove(String isAutoApprove) {
       return Boolean.valueOf(isAutoApprove);
    }

    public  Map<String, Object> getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation( Map<String, Object> additionalInformation) {
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
        List<String> authorities = new ArrayList<>(Arrays.asList(this.authorities.split(",")));
        List<GrantedAuthority> grantedAuthorities = authorities.stream().map(n-> new Authority(n)).collect(Collectors.toList());
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
    public static OauthClient getClientDetailsFromEntity(OauthClient oauthClient){
        oauthClient.setScope(String.valueOf(true));
        return oauthClient;
    }

}