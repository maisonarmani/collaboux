package com.randomsturvs.collaboux.controller;

import com.randomsturvs.collaboux.model.User;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController(value = "/user")
public class UserController {

    @GetMapping(value = "/ping")
    public String sayHello(Principal principal, @RequestParam("ping") String ping){
        return ping;
    }


    @PostMapping(value = "/signon", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String register(Principal principal, @RequestBody @Validated User user){
        return user.getPhoneNumber();
    }

}
