package com.maverik.Testproblem;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

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
        TestProblemResponse response = this.restTemplate.getForObject(
                "http://localhost:" + port + "/movie?title=John",
                TestProblemResponse.class);

        assertThat(response.success());
        assertNotNull(response.data());
        assertThat(response.data() instanceof Movie);

        Movie movie = (Movie) response.data();
        assertThat(movie.title()).contains("John Wick");
    }
}
