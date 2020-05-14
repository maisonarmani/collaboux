package com.randomsturvs.collaboux.services;

import com.randomsturvs.collaboux.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public List<String> getAll(){
        return genreRepository.getAllName();
    }

}
