package com.exercise.salaries2021.services;

import com.exercise.salaries2021.excelparser.PersonSheetParser;
import com.exercise.salaries2021.exception.InvalidQueryParameterException;
import com.exercise.salaries2021.model.Person;
import com.exercise.salaries2021.repository.PersonRepository;
import com.exercise.salaries2021.utils.PersonDbUtility;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class PersonService{

    @Autowired
    private PersonRepository repository;
    @Autowired
    private PersonDbUtility personQuery;


    public String addPersonToDB(Person person) {
        repository.save(person);
        return "Added Person with ID: " + person.getId();
    }

    public List<Person> readFromFile() {
        List<Person> personList = null;
        try {
            PersonSheetParser parser = new PersonSheetParser();
            personList = parser.initializePersons();
        } catch (NullPointerException | IOException e) {
            System.out.println("Something went wrong: " + e.getLocalizedMessage());
        }
        return personList;
    }

    public void uploadFileData() {
        try {
            PersonSheetParser parser = new PersonSheetParser();
            parser.upload(repository, parser.initializePersons());
        } catch (IllegalStateException | IOException | NullPointerException e) {
            System.out.println("Something went wrong: " + e.getLocalizedMessage());
        }
    }

    public List<Person> higherSalary(double value) {

        return personQuery.queryForHighSalaries(value);
    }
    public List<Person> lowerSalary(double value) {
        return personQuery.queryForLowSalaries(value);
    }
    public List<Person> firstNameFinder(String firstName) {
        return personQuery.findFirstName(firstName);
    }
    public List<Person> lastNameFinder(String lastName) {
        return personQuery.findLastName(lastName);
    }
    public List<Person> fullNameFinder(String fullName) {
        return personQuery.findFullName(fullName);
    }
    public List<Person> findTopFiftySalaries(int amount) {
        return personQuery.topFifty(amount);
    }
}
