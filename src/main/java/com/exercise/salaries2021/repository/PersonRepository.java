package com.exercise.salaries2021.repository;

import com.exercise.salaries2021.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, String> {
}
