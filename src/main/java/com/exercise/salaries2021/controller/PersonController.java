package com.exercise.salaries2021.controller;


import com.exercise.salaries2021.excelparser.PersonSheetParser;
import com.exercise.salaries2021.model.Person;
import com.exercise.salaries2021.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("file")
public class PersonController {

    private final MongoTemplate mongoTemplate;

    @Autowired
    private PersonRepository repository;

    public PersonController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @PostMapping("/addPerson")
    public String savePerson(@RequestBody Person person) {
        repository.save(person);
        return "Added Person with ID: " + person.getId();
    }


    @GetMapping("/import")
    public void importExcel() throws IOException, NullPointerException {
        PersonSheetParser parser = new PersonSheetParser();
        try {
            parser.upload(repository, parser.initializePersons());
        } catch (IllegalStateException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }
    @GetMapping("/readExcel")
    public List<Person> readExcel() throws IOException {
        PersonSheetParser parser = new PersonSheetParser();
        List<Person> personList = null;
        try {
            personList = parser.initializePersons();
        } catch (NullPointerException e) {
            e.getMessage();
        }
        return personList;
    }
    @GetMapping("/highSalary")
    public List<Person> highSalary(@RequestParam double value) {
        Query query = new Query();
        query.addCriteria(Criteria.where("salary").gt(value));
        return mongoTemplate.find(query, Person.class);
    }

    @GetMapping("/lowSalary")
    public List<Person> lowSalary(@RequestParam double value) {
        Query query = new Query();
        query.addCriteria(Criteria.where("salary").lt(value));
        return mongoTemplate.find(query, Person.class);
    }

    @GetMapping("/findByFirstName")
    public List<Person> findByFirstName(@RequestParam String firstName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("firstName").is(firstName));
        return mongoTemplate.find(query, Person.class);
    }

    @GetMapping("/findByLastName")
    public List<Person> findByLastName(@RequestParam String lastName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("lastName").is(lastName));
        return mongoTemplate.find(query, Person.class);
    }

    @GetMapping("/findByFullName")
    public List<Person> findByFullName(@RequestParam String fullName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("fullName").is(fullName));
        return mongoTemplate.find(query, Person.class);
    }

    @GetMapping("/topFiftySalaries")
    public List<Person> topFiftySalaries(@RequestParam int amount) {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "salary"));
        query.limit(amount);
        return mongoTemplate.find(query, Person.class);
    }
}
