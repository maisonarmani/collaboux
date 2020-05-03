package com.randomsturvs.collaboux.models;

import com.randomsturvs.collaboux.entity.User;
import com.randomsturvs.collaboux.repository.UserRepository;
import com.randomsturvs.collaboux.model.CurrentUser;
import com.randomsturvs.collaboux.exceptions.ResourceNotFoundException;
import com.randomsturvs.collaboux.principal.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasAnyAuthority('USER_PENDING_CONFIRMATION','USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }

    @GetMapping("/upload")
    public Boolean uploadProfile(@CurrentUser UserPrincipal userPrincipal) {
        return false;
    }
}