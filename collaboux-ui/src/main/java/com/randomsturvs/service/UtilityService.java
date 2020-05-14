package com.randomsturvs.service;

import com.randomsturvs.collaboux.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilityService {

    @Autowired
    private GenreService genreService;

    @Cacheable(value="genres")
    public List<String> getAllGenre(){
        return  genreService.getAll();
    }
}
