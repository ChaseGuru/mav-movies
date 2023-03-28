package com.maverik.Testproblem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maverik.Testproblem.models.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
}