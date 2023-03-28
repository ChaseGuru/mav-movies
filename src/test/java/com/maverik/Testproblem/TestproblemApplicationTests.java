package com.maverik.Testproblem;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.maverik.Testproblem.controllers.MovieController;

@SpringBootTest
class TestproblemApplicationTests {
	@Autowired
	private MovieController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();

	}

}
