package sample.view;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.Date;

public class AddingStudentsForm {

    private static final String ADD_STUDENT_LABEL = "Введите данные для добавления нового студента: ";
    private static final String NAME_LABEL = "Имя: ";
    private static final String SURNAME_LABEL = "Фамилия: ";
    private static final String PATRONYMIC_LABEL = "Отчество: ";
    private static final String DATE_OF_BIRTH_LABEL = "Дата рождения: ";
    private static final String DATE_OF_RECEIPT_LABEL = "Дата поступления: ";
    private static final String DATE_OF_GRADUATION_LABEL = "Дата окончнания: ";


    private GridPane root;
    private TextField surname;
    private TextField name;
    private TextField patronymic;

    private DatePicker dateOfBirth;
    private DatePicker dateOfReceipt;
    private DatePicker dateOfGraduation;

    AddingStudentsForm() {

        surname = new TextField();
        name = new TextField();
        patronymic = new TextField();

        dateOfBirth = new DatePicker();
        dateOfReceipt = new DatePicker();
        dateOfGraduation = new DatePicker();


        root = addingStudentsFormInit();

    }

    private GridPane addingStudentsFormInit()
    {
        root = new GridPane();
        Label addStudentLabel = new Label(ADD_STUDENT_LABEL);
        Label nameLabel =new Label(NAME_LABEL);
        Label surnameLabel = new Label(SURNAME_LABEL);
        Label patronymicLabel = new Label(PATRONYMIC_LABEL);
        Label dateOfBirthLabel = new Label(DATE_OF_BIRTH_LABEL);
        Label dateOfReceiptLabel = new Label(DATE_OF_RECEIPT_LABEL);
        Label dateOfGraduationLabel = new Label(DATE_OF_GRADUATION_LABEL);

        RowConstraints row = new RowConstraints();
        row.setPercentHeight(16);
        root.getRowConstraints().addAll(row,row,row,row,row,row);

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(20);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(30);
        root.getColumnConstraints().addAll(column1,column2,column1,column2);

        GridPane.setHalignment(dateOfBirthLabel, HPos.RIGHT);
        GridPane.setHalignment(dateOfReceiptLabel, HPos.RIGHT);
        GridPane.setHalignment(dateOfGraduationLabel, HPos.RIGHT);
        GridPane.setHalignment(nameLabel, HPos.RIGHT);
        GridPane.setHalignment(surnameLabel, HPos.RIGHT);
        GridPane.setHalignment(patronymicLabel, HPos.RIGHT);

        root.add(surname, 1, 1);
        root.add(name, 1, 2);
        root.add(patronymic, 1, 3);
        root.add(dateOfBirth, 3, 1);
        root.add(dateOfReceipt, 3, 2);
        root.add(dateOfGraduation, 3, 3);
        root.add(addStudentLabel,0,0,4,1);
        root.add(surnameLabel,0,1);
        root.add(nameLabel,0,2);
        root.add(patronymicLabel, 0,3);
        root.add(dateOfBirthLabel,2,1);
        root.add(dateOfReceiptLabel,2,2);
        root.add(dateOfGraduationLabel,2,3);

        return root;
    }

    public GridPane getRoot() {
        return root;
    }

    public TextField getSurname() {
        return surname;
    }

    public TextField getName() {
        return name;
    }

    public TextField getPatronymic() {
        return patronymic;
    }

    public DatePicker getDateOfBirth() {
        return dateOfBirth;
    }

    public DatePicker getDateOfReceipt() {
        return dateOfReceipt;
    }

    public DatePicker getDateOfGraduation() {
        return dateOfGraduation;
    }
}
