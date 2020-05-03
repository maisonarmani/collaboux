package com.randomsturvs.collaboux.factory;



import com.randomsturvs.collaboux.enums.AuthProviderEnum;
import com.randomsturvs.collaboux.exceptions.OAuth2AuthenticationProcessingException;
import com.randomsturvs.collaboux.model.FacebookOAuth2UserInfo;
import com.randomsturvs.collaboux.model.GoogleOAuth2UserInfo;
import com.randomsturvs.collaboux.model.OAuth2UserInfo;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equalsIgnoreCase(AuthProviderEnum.google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProviderEnum.facebook.toString())) {
            return new FacebookOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}