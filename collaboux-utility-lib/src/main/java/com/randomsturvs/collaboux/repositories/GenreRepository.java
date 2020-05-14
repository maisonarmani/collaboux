package com.randomsturvs.collaboux.repositories;

import com.randomsturvs.collaboux.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    @Query("SELECT name from Genre")
    List<String> getAllName();

}
