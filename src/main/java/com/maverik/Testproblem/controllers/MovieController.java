package com.maverik.Testproblem.controllers;

import java.net.URI;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.maverik.Testproblem.models.ErrorCode;
import com.maverik.Testproblem.models.Movie;
import com.maverik.Testproblem.models.TestProblemResponse;

@RestController
public class MovieController {
    @GetMapping("/movie")
    public TestProblemResponse fetchMovie(@RequestParam(value = "title") String title) {
        String url = "https://gateway.maverik.com/movie/api/movie/title/";

        URI uri = UriComponentsBuilder.fromUriString(url)
                .pathSegment(title)
                .queryParam("source", "web")
                .build()
                .encode()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();

        try {
            Movie[] movies = restTemplate.getForObject(uri, Movie[].class);

            if (movies == null || movies.length == 0) {
                return new TestProblemResponse(
                        false,
                        "No Movie Found",
                        ErrorCode.NO_MOVIE_FOUND,
                        null);
            }

            return new TestProblemResponse(
                    true,
                    null,
                    null,
                    movies[0]);

        } catch (RestClientException e) {
            // TODO: Log, since it may indicate downtime of related service

            return new TestProblemResponse(
                    false,
                    "Failed to fetch movie",
                    ErrorCode.MAVERIK_REQUEST_FAILED,
                    null);
        }
    }

}
