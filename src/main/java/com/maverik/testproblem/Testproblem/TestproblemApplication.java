package com.maverik.testproblem.Testproblem;

import java.net.URI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.maverik.testproblem.Testproblem.models.Movie;
import com.maverik.testproblem.Testproblem.models.TestProblemResponse;

@SpringBootApplication
@RestController
public class TestproblemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestproblemApplication.class, args);
	}

	@GetMapping("/fetch-movie")
	public String fetchMovie(@RequestParam(value = "title") String title) {
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

			// if (movies.length == 0) {
			return new TestProblemResponse(
					true,
					null,
					null,
					movies).toString();
			// }

		} catch (RestClientException e) {
			// TODO: Log, since it may indicate downtime of related service

			return new TestProblemResponse(
					false,
					"Failed to fetch movie",
					TestProblemResponse.ErrorCode.MAVERIK_REQUEST_FAILED,
					null).toString();
		}
	}

}
