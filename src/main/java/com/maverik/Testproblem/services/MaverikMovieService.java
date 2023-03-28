package com.maverik.Testproblem.services;

import java.net.URI;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.maverik.Testproblem.models.MaverikMovie;

@Service
public class MaverikMovieService {
    public MaverikMovie[] searchMovies(String title) {
        String url = "https://gateway.maverik.com/movie/api/movie/title/";

        URI uri = UriComponentsBuilder.fromUriString(url)
                .pathSegment(title)
                .queryParam("source", "web")
                .build()
                .encode()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(uri, MaverikMovie[].class);
    }

    public MaverikMovie getMovie(String id) {
        String url = "https://gateway.maverik.com/movie/api/movie/";

        URI uri = UriComponentsBuilder.fromUriString(url)
                .pathSegment(id)
                .queryParam("source", "web")
                .build()
                .encode()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(uri, MaverikMovie.class);
    }
}
