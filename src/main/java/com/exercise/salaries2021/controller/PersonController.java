package com.exercise.salaries2021.controller;


import com.exercise.salaries2021.model.Person;
import com.exercise.salaries2021.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("file")
public class PersonController {

    @Autowired
    private PersonService personServices;

    @PostMapping("/addPerson")
    public String savePerson(@RequestBody Person person) {
        return personServices.addPersonToDB(person);
    }
    @GetMapping("/import")
    public void importExcel() {
        personServices.uploadFileData();
    }
    @GetMapping("/readExcel")
    public List<Person> readExcel() {
        return personServices.readFromFile();
    }
    @GetMapping("/highSalary")
    public List<Person> highSalary(@RequestParam double value) {
        return personServices.higherSalary(value);
    }
    @GetMapping("/lowSalary")
    public List<Person> lowSalary(@RequestParam double value) {
        return personServices.lowerSalary(value);
    }
    @GetMapping("/findByFirstName")
    public List<Person> findByFirstName(@RequestParam String firstName) {
        return personServices.firstNameFinder(firstName);
    }
    @GetMapping("/findByLastName")
    public List<Person> findByLastName(@RequestParam String lastName) {
        return personServices.lastNameFinder(lastName);
    }
    @GetMapping("/findByFullName")
    public List<Person> findByFullName(@RequestParam String fullName) {
        return personServices.fullNameFinder(fullName);
    }
    @GetMapping("/topFiftySalaries")
    public List<Person> topFiftySalaries(@RequestParam int amount) {
        return personServices.findTopFiftySalaries(amount);
    }
}