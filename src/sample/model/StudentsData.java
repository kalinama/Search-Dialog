package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collection;
import java.util.List;

public class StudentsData {


    private ObservableList<Student> students;

    public StudentsData() {
        students = FXCollections.observableArrayList();
    }

    public void removeStudent(Student student)
    {
        students.removeIf(element -> element.equals(student));
    }

    public void generate(Collection<Student> students)
    {
        this.students.addAll(students);
    }

    public void addStudent(Student student)
    {
        students.add(student);
    }

    public void removeAllStudents()
    {
        students.clear();
    }

    public ObservableList<Student> getStudents() {
        return FXCollections.observableArrayList(students);
    }
}