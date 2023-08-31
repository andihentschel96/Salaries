package com.exercise.salaries2021.utils;

import com.exercise.salaries2021.model.Person;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DBQueries {

    private final MongoTemplate mongoTemplate;


    public List<Person> queryForLowSalaries(double value) {

        Query query = new Query();
        query.addCriteria(Criteria.where("salary").lt(value));
        return mongoTemplate.find(query, Person.class);
    }

    public List<Person> queryForHighSalaries(double value) {

        Query query = new Query();
        query.addCriteria(Criteria.where("salary").gt(value));
        return mongoTemplate.find(query, Person.class);
    }

    public List<Person> findFirstName(String firstName) {

        Query query = new Query();
        query.addCriteria(Criteria.where("firstName").is(firstName));
        return mongoTemplate.find(query, Person.class);
    }

    public List<Person> findLastName(String lastName) {

        Query query = new Query();
        query.addCriteria(Criteria.where("lastName").is(lastName));
        return mongoTemplate.find(query, Person.class);
    }

    public List<Person> findFullName(String fullName) {

        Query query = new Query();
        query.addCriteria(Criteria.where("fullName").is(fullName));
        return mongoTemplate.find(query, Person.class);
    }

    public List<Person> topFifty(int amount) {

        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "salary"));
        query.limit(amount);
        return mongoTemplate.find(query, Person.class);
    }
}
