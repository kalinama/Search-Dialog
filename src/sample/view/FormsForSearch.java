package sample.view;

import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

class FormsForSearch {

    private static final String NAME_LABEL = "Имя: ";
    private static final String SURNAME_LABEL = "Фамилия: ";
    private static final String PATRONYMIC_LABEL = "Отчество: ";
    private static final String DAY_LABEL = "День: ";
    private static final String YEAR_MAX__LABEL = "Год(верхний предел): ";
    private static final String YEAR_MIN_LABEL = "Год(нижний предел): ";
    private static final String MONTH_LABEL = "Месяц: ";

    private TextField name;
    private TextField surname;
    private TextField patronymic;

    private TextField day;
    private TextField month;
    private TextField maxLimitYear;
    private TextField minLimitYear;

    FormsForSearch()
    {
        name = new TextField();
        surname = new TextField();
        patronymic = new TextField();
        day = new TextField();
        month = new TextField();
        maxLimitYear = new TextField();
        minLimitYear = new TextField();
    }


    GridPane fullNameAndDay() {

        GridPane root = new GridPane();

        Label nameLabel =new Label(NAME_LABEL);
        Label surnameLabel = new Label(SURNAME_LABEL);
        Label patronymicLabel = new Label(PATRONYMIC_LABEL);
        Label dayLabel = new Label(DAY_LABEL);

        RowConstraints row = new RowConstraints();
        row.setPercentHeight(50);
        root.getRowConstraints().addAll(row,row);

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(20);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(30);
        root.getColumnConstraints().addAll(column1,column2,column1,column2);

        GridPane.setHalignment(dayLabel, HPos.RIGHT);
        GridPane.setHalignment(nameLabel, HPos.RIGHT);
        GridPane.setHalignment(surnameLabel, HPos.RIGHT);
        GridPane.setHalignment(patronymicLabel, HPos.RIGHT);


        root.add(name, 1, 1);
        root.add(surname, 1, 0);
        root.add(patronymic, 3, 0);
        root.add(day, 3, 1);
        root.add(surnameLabel,0,0);
        root.add(nameLabel,0,1);
        root.add(patronymicLabel,2,0);
        root.add(dayLabel,2,1);

        return root;
    }

    GridPane yearAndDay() {
        GridPane root = new GridPane();
        Label yearMinLabel = new Label(YEAR_MIN_LABEL);
        Label yearMaxLabel = new Label(YEAR_MAX__LABEL);
        Label dayLabel = new Label(DAY_LABEL);

        RowConstraints row = new RowConstraints();
        row.setPercentHeight(33);
        root.getRowConstraints().addAll(row,row,row);

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(40);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(60);
        root.getColumnConstraints().addAll(column1,column2);

        root.add(day, 1, 0);
        root.add(maxLimitYear, 1, 2);
        root.add(minLimitYear, 1, 1);
        root.add(dayLabel,0,0);
        root.add(yearMinLabel,0,1);
        root.add(yearMaxLabel,0,2);

        return root;
    }

    GridPane monthAndDay() {
        GridPane root = new GridPane();
        Label monthLabel = new Label(MONTH_LABEL);
        Label dayLabel = new Label(DAY_LABEL);

        RowConstraints row = new RowConstraints();
        row.setPercentHeight(33);
        root.getRowConstraints().addAll(row,row);

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(40);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(60);
        root.getColumnConstraints().addAll(column1,column2);

        root.add(day, 1, 0);
        root.add(month, 1, 1);
        root.add(dayLabel,0,0);
        root.add(monthLabel,0,1);

        return root;
    }

    TextField getName() {
        return name;
    }

    TextField getSurname() {
        return surname;
    }

    TextField getPatronymic() {
        return patronymic;
    }

    TextField getDay() {
        return day;
    }

    TextField getMonth() {
        return month;
    }

    TextField getMaxLimitYear() {
        return maxLimitYear;
    }

    TextField getMinLimitYear() {
        return minLimitYear;
    }
}
