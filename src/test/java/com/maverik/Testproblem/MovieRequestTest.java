package com.maverik.Testproblem;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import com.maverik.Testproblem.models.Movie;
import com.maverik.Testproblem.models.TestProblemResponse;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MovieRequestTest {
    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void movieFetchAndSaveShouldReturnFoundMovie() throws Exception {
        // TODO: Mock requests from maverik movie service
        ParameterizedTypeReference<TestProblemResponse<Movie>> responseType = new ParameterizedTypeReference<TestProblemResponse<Movie>>() {
        };

        RequestEntity<Void> request = RequestEntity.get("http://localhost:" + port + "/fetch-movie?title=John")
                .accept(MediaType.APPLICATION_JSON).build();

        ResponseEntity<TestProblemResponse<Movie>> response = restTemplate.exchange(request, responseType);

        assertNotNull(response);
        assertThat(response.getStatusCode() == HttpStatus.OK);
        TestProblemResponse<Movie> body = response.getBody();

        assertNotNull(body);
        assertThat(body.data().getTitle().contains("John Wick"));

        request = RequestEntity.get("http://localhost:" + port + "/movies/" + body.data()
                .getImdbID())
                .accept(MediaType.APPLICATION_JSON).build();

        ResponseEntity<Movie> savedMovie = restTemplate.exchange(request, new ParameterizedTypeReference<Movie>() {
        });

        assertThat(savedMovie.getStatusCode() == HttpStatus.OK);
        Movie singleBody = savedMovie.getBody();
        assertNotNull(singleBody);
        assertThat(singleBody.getTitle().contains("John Wick"));
    }

    // TODO: Test other response types of method
}
