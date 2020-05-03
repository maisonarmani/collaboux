package com.randomsturvs.collaboux.services;
import com.randomsturvs.collaboux.entity.*;
import com.randomsturvs.collaboux.repository.RoleAuthorityRepository;
import com.randomsturvs.collaboux.repository.RoleRepository;
import com.randomsturvs.collaboux.repository.UserRepository;
import com.randomsturvs.collaboux.exceptions.ResourceNotFoundException;
import com.randomsturvs.collaboux.principal.UserPrincipal;
import com.randomsturvs.collaboux.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomLocalUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    RoleAuthorityRepository roleAuthorityRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                        new UsernameNotFoundException("User not found with email : " + email));

        user = includeUserAuthority(user);
        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );

        user = includeUserAuthority(user);
        return UserPrincipal.create(user);
    }


    private User includeUserAuthority(User user){
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        List<UserRole> userRoles = userRoleRepository.findAllByUser(user);

        for(UserRole userRole : userRoles){
            List<RoleAuthority> roleAuthorities = roleAuthorityRepository.findAllByRole(userRole.getRole());
            grantedAuthorities.add(new Authority(userRole.getRole().getName()));
            for(RoleAuthority roleAuthority : roleAuthorities){
                grantedAuthorities.add(new Authority(roleAuthority.getAuthority().getName()));
            }
        }

        user.setGrantedAuthorities(grantedAuthorities);
        return user;
    }
}