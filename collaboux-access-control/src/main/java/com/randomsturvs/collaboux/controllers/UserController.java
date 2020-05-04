package com.randomsturvs.collaboux.controllers;

import com.randomsturvs.collaboux.model.CurrentUser;
import com.randomsturvs.collaboux.models.UserResponse;
import com.randomsturvs.collaboux.principal.UserPrincipal;
import com.randomsturvs.collaboux.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/me")
    @PreAuthorize("hasAnyAuthority('USER_PENDING_CONFIRMATION','USER')")
    public UserResponse getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return new UserResponse(userService.getUser(userPrincipal));
    }

    @GetMapping("/upload")
    public Boolean uploadProfile(@CurrentUser UserPrincipal userPrincipal) {
        return false;
    }
}