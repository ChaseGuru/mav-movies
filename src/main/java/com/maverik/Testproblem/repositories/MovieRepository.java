package com.maverik.Testproblem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maverik.Testproblem.models.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByTitleContaining(String title);
}