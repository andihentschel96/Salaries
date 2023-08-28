package com.exercise.salaries2021;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class Salaries2021Application {

	public static void main(String[] args) {
		SpringApplication.run(Salaries2021Application.class, args);
	}

}
