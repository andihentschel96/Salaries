package com.exercise.salaries2021.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Getter
@Setter
@ToString

@Document(collection = "SalaryList")
public class Person {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String taxId;
    private String company;
    private String homeTown;
    private double salary;
    private String occupation;
    private String notes;

    public Person() {}
}
