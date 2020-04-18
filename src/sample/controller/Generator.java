package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.FullName;
import sample.model.Student;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

class Generator {

    ObservableList<Student> generate() throws IOException {
        List<String> names = Files.readAllLines(Paths.get("name.txt"),Charset.defaultCharset());
        List<String> surnames = Files.readAllLines(Paths.get("surname.txt"),Charset.defaultCharset());
        List<String> patronymics = Files.readAllLines(Paths.get("patronymic.txt"),Charset.defaultCharset());

        ObservableList<Student> students = FXCollections.observableArrayList();
        int numberOfStudents = 50;
        for (int studentNumber = 0; studentNumber<numberOfStudents; studentNumber++)
        {
            FullName fullName = new FullName(surnames.get((int) (Math.random() * surnames.size())),
                    names.get((int) (Math.random() * names.size())), patronymics.get((int) (Math.random() * patronymics.size()))) ;

            LocalDate dateOfBirth = LocalDate.of((int) (Math.random() * (1979-1950+1)) + 1950,
                    (int) (Math.random() * (12-1+1)) + 1, (int) (Math.random() * (28-1+1)) + 1);

            LocalDate dateOfReceipt = LocalDate.of(dateOfBirth.getYear()+(int) (Math.random() * (40-18+1)) + 18,
                    (int) (Math.random() * (12-1+1)) + 1, (int) (Math.random() * (28-1+1)) + 1);

            LocalDate dateOfGraduation = LocalDate.of(dateOfReceipt.getYear()+(int) (Math.random() * (6-4+1)) + 4,
                    (int) (Math.random() * (12-1+1)) + 1, (int) (Math.random() * (28-1+1)) + 1);

            Student student = new Student(fullName, dateOfBirth, dateOfReceipt, dateOfGraduation);
            students.add(student);

        }
        return  students;
    }
}
