package sample.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import sample.model.FullName;
import sample.model.Student;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class TableForm {


    private static String KOL_PRINT_NOTES = "Записей на странице:";
    private static String KOL_NOTES = "Записей всего:";
    private static String INPUT_KOL_NOTES = "Введите количество записей на странице: ";

    private GridPane rootOfTableForm;
    private List<ObservableList<Student>> listOfStudentsForTablePrint;
    private ObservableList<Student> students;
    private TableView<Student> table;
    private Label kolNotes;
    private Label kolPrintNotes;
    private Label pageNumber;


    private int amountOfStudents;
    private int amountOfPages;
    private int currentPage;
    private int amountOfStudentsOnLastPage;

    TableForm(ObservableList<Student> students) {

        this.listOfStudentsForTablePrint = new ArrayList<>();
        this.students = students;

        amountOfPages = 1;
        amountOfStudents = 10;
        currentPage = 1;
        amountOfStudentsOnLastPage = 0;
        kolNotes = new Label();
        kolPrintNotes = new Label();
        pageNumber = new Label();


        this.rootOfTableForm = tableFormInit();

    }

    private void createTable()
    {
        table = new TableView<>();
        renewTable();
        table.setPrefWidth(300);
        table.setPrefHeight(300);

        TableColumn<Student, FullName> fullNameColumn = new TableColumn<>("ФИО");
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        fullNameColumn.setPrefWidth(300);

        TableColumn<Student, LocalDate> dateOfBirthColumn = new TableColumn<>("Дата рождения");
        dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        dateOfBirthColumn.setPrefWidth(150);

        TableColumn<Student, LocalDate> dateOfReceiptColumn = new TableColumn<>("Дата поступления");
        dateOfReceiptColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfReceipt"));
        dateOfReceiptColumn.setPrefWidth(150);

        TableColumn<Student, LocalDate> dateOfGraduationColumn = new TableColumn<>("Дата окончания");
        dateOfGraduationColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfGraduation"));
        dateOfGraduationColumn.setPrefWidth(150);

        table.getColumns().addAll(fullNameColumn, dateOfBirthColumn, dateOfReceiptColumn, dateOfGraduationColumn);

    }

    private GridPane tableFormInit()
    {
        createTable();
        kolNotes.setText(KOL_NOTES + " 0");
        kolPrintNotes.setText(KOL_PRINT_NOTES + "  0" );
        pageNumber.setText("Страница 1 из 1");
        Label inputKolNotes = new Label(INPUT_KOL_NOTES);



        Image firstPage = new Image(new File("first.png").toURI().toString(),30,
                30,true,true);
        Image lastPage = new Image(new File("last.png").toURI().toString(),30,
                30,true,true);
        Image nextPage = new Image(new File("next.png").toURI().toString(),30,
                30,true,true);
        Image previousPage = new Image(new File("previous.png").toURI().toString(),30,
                30,true,true);

        ImageView firstPageImage = new ImageView(firstPage);
        ImageView lastPageImage = new ImageView(lastPage);
        ImageView nextPageImage = new ImageView(nextPage);
        ImageView previousPageImage = new ImageView(previousPage);

        Button buttonChangeNumberOfData = new Button("Изменить");
        Button buttonNext = new Button("",nextPageImage);
        Button buttonPrevious = new Button("",previousPageImage);
        Button buttonLast = new Button("", lastPageImage);
        Button buttonFirst = new Button("",firstPageImage);



        TextField textFieldNumberOfData = new TextField();

        GridPane root = new GridPane();

        buttonChangeNumberOfData.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(new Scanner(textFieldNumberOfData.getText()).hasNextInt()&&Integer.parseInt(textFieldNumberOfData.getText())>0) {
                    amountOfStudents = Integer.parseInt(textFieldNumberOfData.getText());
                    renewTable();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Количество записей на странице не было изменено");
                    alert.setContentText("Допускается лишь ввод чисел больше нуля!");
                    alert.show();
                }

            }
        });

        buttonFirst.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setTable(1);
            }
        });
        buttonNext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (listOfStudentsForTablePrint.size()>=currentPage+1) {
                    setTable(currentPage + 1);
                }
            }
        });

        buttonLast.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setTable(amountOfPages);
            }
        });

        buttonPrevious.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (currentPage!=1) {
                    setTable(currentPage - 1);
                }
            }
        });


        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(24);

        root.getColumnConstraints().add(column);
        root.getColumnConstraints().add(column);
        root.getColumnConstraints().add(column);
        root.getColumnConstraints().add(column);

        RowConstraints row = new RowConstraints();
        row.setPercentHeight(7);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(68);
        RowConstraints row3 = new RowConstraints();
        row3.setPercentHeight(10);
        root.getRowConstraints().add(row);
        root.getRowConstraints().add(row2);
        root.getRowConstraints().add(row);
        root.getRowConstraints().add(row3);
        root.getRowConstraints().add(row);

        //root.setGridLinesVisible(true);
        GridPane.setHalignment(buttonChangeNumberOfData, HPos.LEFT);
        GridPane.setHalignment(inputKolNotes, HPos.RIGHT);
        GridPane.setHalignment(pageNumber, HPos.CENTER);
        GridPane.setHalignment(buttonPrevious, HPos.RIGHT);
        GridPane.setValignment(buttonPrevious, VPos.CENTER);
        GridPane.setHalignment(buttonNext, HPos.LEFT);
        GridPane.setValignment(buttonNext, VPos.CENTER);
        GridPane.setHalignment(buttonLast, HPos.RIGHT);
        GridPane.setValignment(buttonLast, VPos.CENTER);

        root.add(table, 0, 1, 4, 1);
        root.add(buttonFirst,0,3);
        root.add(buttonNext,2,3);
        root.add(buttonPrevious,1,3);
        root.add(buttonLast,3,3);
        root.add(kolNotes,0,0);
        root.add(kolPrintNotes,3,0);
        root.add(pageNumber,0,2,4,1);
        root.add(inputKolNotes,0,4,2,1);
        root.add(buttonChangeNumberOfData, 3, 4);
        root.add(textFieldNumberOfData, 2, 4);

        return root;
    }

    void renewTable()
    {
        listOfStudentsForTablePrint.clear();

        amountOfPages = students.size() / amountOfStudents;

        for (int pageNumber = 0; pageNumber < amountOfPages; pageNumber++) {

            ObservableList<Student> listForPage = FXCollections.observableArrayList();
            for (int noteNumber = 0; noteNumber < amountOfStudents; noteNumber++) {
                listForPage.add(students.get(pageNumber * amountOfStudents + noteNumber));
            }
            listOfStudentsForTablePrint.add(listForPage);
        }

        if (students.size() % amountOfStudents != 0) {
            ObservableList<Student> listForPage = FXCollections.observableArrayList();
            amountOfStudentsOnLastPage = students.size() % amountOfStudents;
            for (int noteNumber = 0; noteNumber < students.size() % amountOfStudents; noteNumber++) {
                listForPage.add(students.get(amountOfPages * amountOfStudents + noteNumber));
            }
            listOfStudentsForTablePrint.add(listForPage);
            amountOfPages++;
        }
        kolNotes.setText(KOL_NOTES+" "+ students.size());
        if (currentPage>amountOfPages) setTable(1);
        setTable(currentPage);
    }

    private void setTable(int page) {
        if (listOfStudentsForTablePrint.size() == 0) {
            ObservableList<Student> empty = FXCollections.observableArrayList();
            listOfStudentsForTablePrint.add(empty);
            table.setItems(listOfStudentsForTablePrint.get(0));
        }
          else  table.setItems(listOfStudentsForTablePrint.get(page-1));
          currentPage = page;

          if (amountOfPages==0) amountOfPages=1;
          pageNumber.setText("Страница "+ currentPage+" из "+ amountOfPages);
          if (currentPage==amountOfPages)  kolPrintNotes.setText(KOL_PRINT_NOTES + " " +amountOfStudentsOnLastPage);
          else  kolPrintNotes.setText(KOL_PRINT_NOTES + " " +amountOfStudents);

    }

    void clearList()
    {
        listOfStudentsForTablePrint.clear();
        setAmountOfPages(1);

        ObservableList<Student> empty = FXCollections.observableArrayList();
        listOfStudentsForTablePrint.add(empty);
    }
    GridPane getRootOfTableForm() {
        return rootOfTableForm;
    }



    private void setAmountOfPages(int amountOfPages) {

        this.amountOfPages = amountOfPages;
    }
}
