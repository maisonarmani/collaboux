package com.randomsturvs.controller;

import com.randomsturvs.service.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/utility")
public class UtilityController {

    @Autowired
    private UtilityService utilityService;

    @GetMapping("/genres")
    public List<String> getGenres(){
        return utilityService.getAllGenre();
    }
}