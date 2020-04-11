package com.randomsturvs.collaboux.controllers;

import com.randomsturvs.collaboux.social.models.UserPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/samples")
public class SampleController {

    @GetMapping("")
    public String sample(UserPrincipal userPrincipal){
        return "Has error";
    }

}
