package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.FullName;
import sample.model.Student;
import sample.model.StudentsData;

public class SearchStudentsController {

    private StudentsData studentsData;
    private  ObservableList<Student> students;

    public SearchStudentsController(StudentsData studentsData) {
        this.studentsData = studentsData;
        this.students = studentsData.getStudents();

    }
    private void updateData()
    {
        students.setAll(studentsData.getStudents());
    }

    public ObservableList<Student> searchByFullNameAndDayOfBirth(String name, String surname, String patronymic, int dayOfBirth) {
        updateData();

        ObservableList<Student> detectedStudents = FXCollections.observableArrayList();
        for (Student student : students) {
            FullName fullName = student.getFullName();
            if (!fullName.getName().equals(name))
                continue;
            if (!fullName.getSurname().equals(surname))
                continue;
            if (!fullName.getPatronymic().equals(patronymic))
                continue;
            if (student.getDateOfBirth().getDayOfMonth() != dayOfBirth)
                continue;
            detectedStudents.add(student);
        }
        return detectedStudents;

    }

    public ObservableList<Student> searchByYearAndDayOfBirth(int day, int maxLimitYear, int minLimitYear) {

        updateData();
        ObservableList<Student> detectedStudents = FXCollections.observableArrayList();

        for (Student student : students) {

            if (student.getDateOfBirth().getDayOfMonth() != day)
                continue;
            if (student.getDateOfBirth().getYear() < minLimitYear || student.getDateOfBirth().getYear() > maxLimitYear)
                continue;

            detectedStudents.add(student);
        }
        return detectedStudents;

    }

    public ObservableList<Student> searchByMonthAndDayOfBirth(int month, int day) {

        updateData();
        ObservableList<Student> detectedStudents = FXCollections.observableArrayList();
        for (Student student : students) {

            if (student.getDateOfBirth().getDayOfMonth() != day)
                continue;
            if (student.getDateOfBirth().getMonthValue() != month)
                continue;

            detectedStudents.add(student);
        }
        return detectedStudents;

    }

    public ObservableList<Student> searchByFullNameAndDayOfReceipt(String name, String surname, String patronymic, int dayOfBirth) {

        updateData();
        ObservableList<Student> detectedStudents = FXCollections.observableArrayList();
        for (Student student : students) {
            if (!student.getFullName().getName().equals(name))
                continue;
            if (!student.getFullName().getSurname().equals(surname))
                continue;
            if (!student.getFullName().getPatronymic().equals(patronymic))
                continue;
            if (student.getDateOfReceipt().getDayOfMonth() != dayOfBirth)
                continue;
            detectedStudents.add(student);
        }
        return detectedStudents;

    }

    public ObservableList<Student> searchByYearAndDayOfReceipt(int day, int maxLimitYear, int minLimitYear) {

        updateData();
        ObservableList<Student> detectedStudents = FXCollections.observableArrayList();

        for (Student student : students) {

            if (student.getDateOfReceipt().getDayOfMonth() != day)
                continue;
            if (student.getDateOfReceipt().getYear() < minLimitYear || student.getDateOfReceipt().getYear() > maxLimitYear)
                continue;

            detectedStudents.add(student);
        }
        return detectedStudents;

    }

    public ObservableList<Student> searchByMonthAndDayOfReceipt(int month, int day) {

        updateData();
        ObservableList<Student> detectedStudents = FXCollections.observableArrayList();
        for (Student student : students) {

            if (student.getDateOfReceipt().getDayOfMonth() != day)
                continue;
            if (student.getDateOfReceipt().getMonthValue() != month)
                continue;

            detectedStudents.add(student);
        }
        return detectedStudents;

    }

    public ObservableList<Student> searchByFullNameAndDayOfGraduation(String name, String surname, String patronymic, int dayOfBirth) {

        updateData();
        ObservableList<Student> detectedStudents = FXCollections.observableArrayList();
        for (Student student : students) {
            if (!student.getFullName().getName().equals(name))
                continue;
            if (!student.getFullName().getSurname().equals(surname))
                continue;
            if (!student.getFullName().getPatronymic().equals(patronymic))
                continue;
            if (student.getDateOfGraduation().getDayOfMonth() != dayOfBirth)
                continue;
            detectedStudents.add(student);
        }
        return detectedStudents;

    }

    public ObservableList<Student> searchByYearAndDayOfGraduation(int day, int maxLimitYear, int minLimitYear) {

        updateData();
        ObservableList<Student> detectedStudents = FXCollections.observableArrayList();

        for (Student student : students) {

            if (student.getDateOfGraduation().getDayOfMonth() != day)
                continue;
            if (student.getDateOfGraduation().getYear() < minLimitYear || student.getDateOfGraduation().getYear() > maxLimitYear)
                continue;

            detectedStudents.add(student);
        }
        return detectedStudents;
    }

    public ObservableList<Student> searchByMonthAndDayOfGraduation(int month, int day) {

        updateData();
        ObservableList<Student> detectedStudents = FXCollections.observableArrayList();
        for (Student student : students) {

            if (student.getDateOfGraduation().getDayOfMonth() != day)
                continue;
            if (student.getDateOfGraduation().getMonthValue() != month)
                continue;

            detectedStudents.add(student);
        }
        return detectedStudents;

    }

}