package com.maverik.Testproblem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.maverik.Testproblem.models.ErrorCode;
import com.maverik.Testproblem.models.MaverikMovie;
import com.maverik.Testproblem.models.TestProblemResponse;
import com.maverik.Testproblem.services.MaverikMovieService;

@RestController
public class MovieController {
    @Autowired
    MaverikMovieService movieService;

    @GetMapping("/movie")
    public ResponseEntity<TestProblemResponse<MaverikMovie>> fetchMovie(@RequestParam(value = "title") String title) {
        try {
            MaverikMovie[] movies = movieService.searchMovies(title);
            if (movies == null || movies.length == 0) {
                return new ResponseEntity<>(
                        new TestProblemResponse<MaverikMovie>(
                                false,
                                "No Movie Found",
                                ErrorCode.NO_MOVIE_FOUND,
                                null),
                        HttpStatus.NOT_FOUND);
            }

            MaverikMovie movie = movieService.getMovie(movies[0].imdbID());
            if (movie == null) {
                return new ResponseEntity<>(
                        new TestProblemResponse<MaverikMovie>(
                                false,
                                "No Movie Found",
                                ErrorCode.NO_MOVIE_FOUND,
                                null),
                        HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(
                    new TestProblemResponse<MaverikMovie>(
                            true,
                            null,
                            null,
                            movies[0]),
                    HttpStatus.OK);

        } catch (RestClientException e) {
            // TODO: Log, since it may indicate downtime of related service

            return new ResponseEntity<>(
                    new TestProblemResponse<MaverikMovie>(
                            false,
                            "Failed to fetch movie",
                            ErrorCode.MAVERIK_REQUEST_FAILED,
                            null),
                    HttpStatus.BAD_GATEWAY);
        }
    }

}
