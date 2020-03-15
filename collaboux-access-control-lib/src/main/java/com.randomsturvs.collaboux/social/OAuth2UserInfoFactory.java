package com.randomsturvs.collaboux.social;



import com.randomsturvs.collaboux.entity.AuthProvider;
import com.randomsturvs.collaboux.social.exceptions.OAuth2AuthenticationProcessingException;
import com.randomsturvs.collaboux.social.models.FacebookOAuth2UserInfo;
import com.randomsturvs.collaboux.social.models.GoogleOAuth2UserInfo;
import com.randomsturvs.collaboux.social.models.OAuth2UserInfo;

import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.facebook.toString())) {
            return new FacebookOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}