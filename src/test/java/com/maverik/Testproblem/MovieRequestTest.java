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
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;

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
        // TODO: put maverik movie requests in service so that we can "mock" responses
        ParameterizedTypeReference<TestProblemResponse<Movie>> responseType = new ParameterizedTypeReference<>() {
        };
        RequestEntity<Void> request = RequestEntity.get("http://localhost:" + port + "/movie?title=John")
                .accept(MediaType.APPLICATION_JSON).build();

        TestProblemResponse<Movie> response = restTemplate.exchange(request, responseType)
                .getBody();

        assertNotNull(response);
        assertThat(response.success());
        assertNotNull(response.data());

        Movie movie = response.data();
        assertThat(movie.title()).contains("John Wick");
    }

    // TODO: Test other response types of method
}
