package com.exercise.salaries2021.services;

import com.exercise.salaries2021.excelparser.PersonSheetParser;
import com.exercise.salaries2021.model.Person;
import com.exercise.salaries2021.repository.PersonRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class DataBaseServices {

    public List<Person> readFromFile() {
        PersonSheetParser parser = new PersonSheetParser();
        List<Person> personList = null;
        try {
            personList = parser.initializePersons();
        } catch (NullPointerException | IOException e) {
            System.out.println("Something went wrong: " + e.getLocalizedMessage());
        }
        return personList;
    }

    public void uploadFileData(PersonRepository repository) {
        PersonSheetParser parser = new PersonSheetParser();
        try {
            parser.upload(repository, parser.initializePersons());
        } catch (IllegalStateException | IOException | NullPointerException e) {
            System.out.println("Something went wrong: " + e.getLocalizedMessage());
        }
    }
}
