package com.randomsturvs.collaboux.eventhandlers;

import com.randomsturvs.collaboux.entity.Role;
import com.randomsturvs.collaboux.entity.UserRole;
import com.randomsturvs.collaboux.enums.RoleEnum;
import com.randomsturvs.collaboux.events.UserSignupEvent;
import com.randomsturvs.collaboux.repository.RoleRepository;
import com.randomsturvs.collaboux.repository.UserRepository;
import com.randomsturvs.collaboux.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class SignupEventListener implements ApplicationListener<UserSignupEvent> {

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    public void onApplicationEvent(UserSignupEvent userSignupEvent) {
        Role role = new Role();

        UserRole userRole =  new UserRole();
        userRole.setUser(userSignupEvent.getUser());

        role.setId(roleRepository.getIdWithName(StringUtils.isEmpty(userSignupEvent.getUser().getPassword()) ?
                RoleEnum.ROLE_UNSET_PASSWORD_USER.toString() : RoleEnum.ROLE_PENDING_CONFIRMATION_USER.toString()));

        userRole.setRole(role);
        userRoleRepository.save(userRole);
    }
}

