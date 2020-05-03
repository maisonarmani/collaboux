package com.randomsturvs.collaboux.services;


import com.randomsturvs.collaboux.entity.Role;
import com.randomsturvs.collaboux.entity.User;
import com.randomsturvs.collaboux.entity.UserRole;
import com.randomsturvs.collaboux.enums.AuthProviderEnum;
import com.randomsturvs.collaboux.enums.RoleEnum;
import com.randomsturvs.collaboux.exceptions.BadRequestException;
import com.randomsturvs.collaboux.model.OAuth2UserInfo;
import com.randomsturvs.collaboux.model.SignUpRequest;
import com.randomsturvs.collaboux.repository.RoleRepository;
import com.randomsturvs.collaboux.repository.UserRepository;
import com.randomsturvs.collaboux.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.PersistenceException;


@Service
public class UserService {


    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Transactional
    public User registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        User user = new User();

        user.setAuthProvider(AuthProviderEnum.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
        user.setName(oAuth2UserInfo.getName());
        user.setEmail(oAuth2UserInfo.getEmail());
        user.setUsername(oAuth2UserInfo.getEmail().split("@")[0]);

        user = userRepository.save(user);

        updateUserRole(user);

        publishEvent(user);

        return user;
    }


    @Transactional
    public User registerNewUser(SignUpRequest signUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.getEmail()) != null) {
            throw new BadRequestException("Email address already in use.");
        }

        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setAuthProvider(AuthProviderEnum.local);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user = userRepository.save(user);

        updateUserRole(user);

        publishEvent(user);

        return user;
    }

    private void publishEvent(User user) {
//        UserSignupEvent userSignupEvent = new UserSignupEvent(this, user);
//        publisher.publishEvent(userSignupEvent);
    }


    @Transactional
    public void updateUserRole(User user) throws PersistenceException {
        Role role = new Role();

        UserRole userRole =  new UserRole();
        userRole.setUser(user);

        role.setId(roleRepository.getIdWithName(StringUtils.isEmpty(user.getPassword()) ?
                RoleEnum.ROLE_UNSET_PASSWORD_USER.toString() : RoleEnum.ROLE_PENDING_CONFIRMATION_USER.toString()));

        if(role.getId() == null){
            throw new PersistenceException("Role for users not properly setup");
        }
        userRole.setRole(roleRepository.findOne(Example.of(role)).get());
        userRoleRepository.save(userRole);
    }

    public User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setName(oAuth2UserInfo.getName());
        //existingUser.setImageUrl(oAuth2UserInfo.getImageUrl());
        return userRepository.save(existingUser);
    }
}