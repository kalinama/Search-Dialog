package sample.model;

import java.time.LocalDate;


public class Student {

    private FullName fullName;
    private LocalDate dateOfBirth;
    private LocalDate dateOfReceipt;
    private LocalDate dateOfGraduation;

    public Student(FullName fullName, LocalDate dateOfBirth, LocalDate dateOfReceipt, LocalDate dateOfGraduation) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.dateOfReceipt = dateOfReceipt;
        this.dateOfGraduation = dateOfGraduation;
    }


    public FullName getFullName() {
        return fullName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public LocalDate getDateOfReceipt() {
        return dateOfReceipt;
    }

    public LocalDate getDateOfGraduation() {
        return dateOfGraduation;
    }

}
