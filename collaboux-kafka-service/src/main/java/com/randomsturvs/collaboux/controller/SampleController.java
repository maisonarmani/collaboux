package com.randomsturvs.collaboux.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/samples")
public class SampleController {

    @GetMapping("")
    @PreAuthorize("hasRole('IS_AUTHENTICATED_ANONYMOUSLY')")
    public String sample(){
        return "Has error";
    }

}
