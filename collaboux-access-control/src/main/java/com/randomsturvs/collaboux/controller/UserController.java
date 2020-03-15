package com.randomsturvs.collaboux.controller;

import com.randomsturvs.collaboux.entity.User;
import com.randomsturvs.collaboux.repository.UserRepository;
import com.randomsturvs.collaboux.social.models.CurrentUser;
import com.randomsturvs.collaboux.social.exceptions.ResourceNotFoundException;
import com.randomsturvs.collaboux.social.models.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        // if the user password has not been set redirect the use to a set password page
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}