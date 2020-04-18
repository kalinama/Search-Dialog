package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.SearchType;
import sample.model.StudentsData;
import sample.model.Student;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class ChangerStudentsData {

    StudentsData studentsData;

    public ChangerStudentsData(StudentsData studentsData) {
        this.studentsData = studentsData;
    }

    public void removeStudent(Student student) {
        studentsData.removeStudent(student);
    }

    public void generate() throws IOException {
        studentsData.generate(new Generator().generate());
    }

    public void addStudent(Student student) {
        studentsData.addStudent(student);
    }

    public void newFile()
    {
        studentsData.removeAllStudents();
    }

    public ObservableList<Student> getData(){
        return studentsData.getStudents();
    }

    public void searchStudents(SearchType searchType) {

    }


}