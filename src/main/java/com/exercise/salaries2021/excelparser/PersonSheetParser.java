package com.exercise.salaries2021.excelparser;

import com.exercise.salaries2021.model.Person;
import com.exercise.salaries2021.repository.PersonRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

@Component
public class PersonSheetParser {

    @Autowired
    private PersonRepository repository;
    private static final String SALARIES_FILE = "src\\main\\resources\\salaries1.xlsx";
    private static final int SHEET_NUMBER = 0;
    private static final DataFormatter dataFormatter = new DataFormatter(Locale.GERMAN);

    public void upload(PersonRepository repository, List<Person> persons) {
        repository.saveAll(persons);
    }

    public List<Person> initializePersons() throws IOException {
        Iterator<Row> rowIterator = getIterator();
        List<Person> persons = new LinkedList<>();
        while (rowIterator.hasNext()) {
            Row currentRow = rowIterator.next();
            Iterator<Cell> cellIterator = currentRow.iterator();
            Person person = new Person();
            while (cellIterator.hasNext()) {
                Cell currentCell = cellIterator.next();
                extractColumn(person, currentCell);
            }
            persons.add(person);
        }
        return persons;
    }

    private void extractColumn(Person person, Cell currentCell) {
        String salaryString;
        String changedDelimiter;
        double salary;

        switch (currentCell.getColumnIndex()) {
            case 0 -> person.setId(dataFormatter.formatCellValue(currentCell));
            case 1 -> person.setFirstName(dataFormatter.formatCellValue(currentCell));
            case 2 -> person.setLastName(dataFormatter.formatCellValue(currentCell));
            case 3 -> person.setFullName(dataFormatter.formatCellValue(currentCell));
            case 4 -> person.setTaxId(dataFormatter.formatCellValue(currentCell));
            case 5 -> person.setCompany(dataFormatter.formatCellValue(currentCell));
            case 6 -> person.setHomeTown(dataFormatter.formatCellValue(currentCell));
            case 7 -> {
                salaryString = dataFormatter.formatCellValue(currentCell);
                changedDelimiter = salaryString.replaceAll(",", ".");
                salary = Double.parseDouble(changedDelimiter);
                person.setSalary(salary);
            }
            case 8 -> person.setOccupation(dataFormatter.formatCellValue(currentCell));
            case 9 -> person.setNotes(dataFormatter.formatCellValue(currentCell));
        }
    }

    private Iterator<Row> getIterator() throws IOException {
        FileInputStream inputStream = new FileInputStream(SALARIES_FILE);
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(SHEET_NUMBER);
        inputStream.close();
        return sheet.iterator();
    }
}
