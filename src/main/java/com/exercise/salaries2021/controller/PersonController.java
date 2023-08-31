package com.exercise.salaries2021.controller;


import com.exercise.salaries2021.model.Person;
import com.exercise.salaries2021.repository.PersonRepository;
import com.exercise.salaries2021.services.DataBaseServices;
import com.exercise.salaries2021.utils.DBQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("file")
public class PersonController {

    @Autowired
    private final MongoTemplate mongoTemplate;
    @Autowired
    private PersonRepository repository;
    @Autowired
    private DBQueries query;
    @Autowired
    private DataBaseServices dataBaseServices;

    public PersonController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @PostMapping("/addPerson")
    public String savePerson(@RequestBody Person person) {
        repository.save(person);
        return "Added Person with ID: " + person.getId();
    }
    @GetMapping("/import")
    public void importExcel() {
        dataBaseServices.uploadFileData(repository);
    }
    @GetMapping("/readExcel")
    public List<Person> readExcel() {
        return dataBaseServices.readFromFile();
    }
    @GetMapping("/highSalary")
    public List<Person> highSalary(@RequestParam double value) {
        return query.queryForHighSalaries(value);
    }
    @GetMapping("/lowSalary")
    public List<Person> lowSalary(@RequestParam double value) {
        return query.queryForLowSalaries(value);
    }
    @GetMapping("/findByFirstName")
    public List<Person> findByFirstName(@RequestParam String firstName) {
        return query.findFirstName(firstName);
    }
    @GetMapping("/findByLastName")
    public List<Person> findByLastName(@RequestParam String lastName) {
        return query.findLastName(lastName);
    }
    @GetMapping("/findByFullName")
    public List<Person> findByFullName(@RequestParam String fullName) {
        return query.findFullName(fullName);
    }
    @GetMapping("/topFiftySalaries")
    public List<Person> topFiftySalaries(@RequestParam int amount) {
        return query.topFifty(amount);
    }
}