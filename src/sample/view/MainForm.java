package sample.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.xml.sax.SAXException;
import sample.SearchType;
import sample.controller.ChangerStudentsData;
import sample.controller.FileLoader;
import sample.controller.SearchStudentsController;
import sample.model.FullName;
import sample.model.Student;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MainForm {

    private static final String SEARCH_PARAMETERS_STUDENT_LABEL = "Введите данные для поиска: ";

    private static final String BUTTON_ADD_STUDENT = "Добавить студента";
    private static final String BUTTON_SEARCH_STUDENT = "Найти";
    private static final String BUTTON_DELETE_STUDENT = "Удалить";

    private static final String FILE_MENU = "Файл";
    private static final String EDIT_MENU = "Редактирование";
    private static final String MENU_ITEM_NEW = "Новый";
    private static final String MENU_ITEM_OPEN = "Открыть";
    private static final String MENU_ITEM_SAVE = "Сохранить";
    private static final String MENU_ITEM_GENERATE = "Сгенерировать";
    private static final String MENU_ITEM_ADD = "Добавить";
    private static final String MENU_ITEM_DELETE_ = "Удалить";
    private static final String MENU_ITEM_SEARCH = "Найти";

    private final static String SEARCH_BY_FULL_NAME_AND_DAY_OF_BIRTH = "Найти по ФИО и дню рождения";
    private final static String SEARCH_BY_YEAR_AND_DAY_OF_BIRTH = "Найти по году и дню рождения";
    private final static String SEARCH_BY_MONTH_AND_DAY_OF_BIRTH = "Найти по месяцу и дню рождения";
    private final static String SEARCH_BY_FULL_NAME_AND_DAY_OF_RECEIPT = "Найти по ФИО и дню поступления";
    private final static String SEARCH_BY_YEAR_AND_DAY_OF_RECEIPT = "Найти по году и дню поступления";
    private final static String SEARCH_BY_MONTH_AND_DAY_OF_RECEIPT = "Найти по месяцу и дню поступления";
    private final static String SEARCH_BY_FULL_NAME_AND_DAY_OF_GRADUATION = "Найти по ФИО и дню окончания";
    private final static String SEARCH_BY_YEAR_AND_DAY_OF_GRADUATION = "Найти по году и дню окончания";
    private final static String SEARCH_BY_MONTH_AND_DAY_OF_GRADUATION = "Найти по месяцу и дню окончания";

    private final static String DELETE_BY_FULL_NAME_AND_DAY_OF_BIRTH = "Удалить по ФИО и дню рождения";
    private final static String DELETE_BY_YEAR_AND_DAY_OF_BIRTH = "Удалить по году и дню рождения";
    private final static String DELETE_BY_MONTH_AND_DAY_OF_BIRTH = "Удалить по месяцу и дню рождения";
    private final static String DELETE_BY_FULL_NAME_AND_DAY_OF_RECEIPT = "Удалить по ФИО и дню поступления";
    private final static String DELETE_BY_YEAR_AND_DAY_OF_RECEIPT = "Удалить по году и дню поступления";
    private final static String DELETE_BY_MONTH_AND_DAY_OF_RECEIPT = "Удалить по месяцу и дню поступления";
    private final static String DELETE_BY_FULL_NAME_AND_DAY_OF_GRADUATION = "Удалить по ФИО и дню окончания";
    private final static String DELETE_BY_YEAR_AND_DAY_OF_GRADUATION = "Удалить по году и дню окончания";
    private final static String DELETE_BY_MONTH_AND_DAY_OF_GRADUATION = "Удалить по месяцу и дню окончания";

    private static final String OPEN_FILE_CHOOSER_TITLE = "Открыть";
    private static final String SAVE_FILE_CHOOSER_TITLE = "Сохранить";

    private ChangerStudentsData changerStudentsData;
    private SearchStudentsController searchStudentsController;
    private FileLoader fileLoader;
    private Stage primaryStage;
    private GridPane root;
    private TableForm tableForm;
    private ObservableList<Student> cloneData;

    public MainForm(ChangerStudentsData changerStudentsData, SearchStudentsController searchStudentsController,
                    FileLoader fileLoader, Stage primaryStage) {
        this.changerStudentsData = changerStudentsData;
        this.searchStudentsController = searchStudentsController;
        this.fileLoader = fileLoader;
        this.primaryStage = primaryStage;
        this.cloneData = changerStudentsData.getData();
        mainFormInit(MainForm.this);

    }

    private void updateCloneData() {
        cloneData.setAll(changerStudentsData.getData());
    }

    void update(TableForm tableForm) {
        tableForm.renewTable();
    }

    private Alert createErrorAlert() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Ошибка");
        errorAlert.setHeaderText("Невозможно произвести поиск студентов с такими данными");
        errorAlert.setContentText("Все поля должны быть заполнены корректно");
        return errorAlert;
    }

    private void mainFormInit(MainForm mainForm) {
        root = new GridPane();
        tableForm = new TableForm(cloneData);
        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(10);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(90);
        root.getColumnConstraints().add(column);
        root.getColumnConstraints().add(column2);

        RowConstraints row = new RowConstraints();
        row.setPercentHeight(7);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(93);
        root.getRowConstraints().add(row);
        root.getRowConstraints().add(row2);

        root.add(tableForm.getRootOfTableForm(), 1, 1);
        root.add(initMenu(mainForm), 0, 0, 2, 1);
        root.add(createToolBar(mainForm), 0, 1);
        //root.setGridLinesVisible(true);
    }

    private void openAddingStudentsAlert(MainForm mainForm) {
        AddingStudentsForm addingStudentsForm = new AddingStudentsForm();
        Alert alertAddingStudents = new Alert(Alert.AlertType.NONE);
        alertAddingStudents.getDialogPane().setContent(addingStudentsForm.getRoot());

        alertAddingStudents.getButtonTypes().add(ButtonType.CLOSE);

        Button buttonAddStudent = new Button(BUTTON_ADD_STUDENT);

        addingStudentsForm.getRoot().add(buttonAddStudent, 0, 5, 5, 1);
        buttonAddStudent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (addingStudentsForm.getName().getText() != null && addingStudentsForm.getSurname().getText() != null &&
                        addingStudentsForm.getPatronymic().getText() != null && addingStudentsForm.getDateOfBirth().getValue() != null &&
                        addingStudentsForm.getDateOfReceipt().getValue() != null && addingStudentsForm.getDateOfGraduation().getValue() != null) {
                    Student student = new Student(new FullName(addingStudentsForm.getSurname().getText(), addingStudentsForm.getName().getText(),
                            addingStudentsForm.getPatronymic().getText()), addingStudentsForm.getDateOfBirth().getValue(),
                            addingStudentsForm.getDateOfReceipt().getValue(), addingStudentsForm.getDateOfGraduation().getValue());
                    changerStudentsData.addStudent(student);
                    updateCloneData();
                    mainForm.update(tableForm);
                } else {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Ошибка");
                    errorAlert.setHeaderText("Невозможно добавить студента с такими данными!");
                    errorAlert.setContentText("Для добавления студента необходимо, чтобы все поля были заполнены");
                    errorAlert.show();
                }
            }
        });

        alertAddingStudents.showAndWait();
    }

    private void openSearchingStudentsAlertWithComboBox(MainForm mainForm) {

        ObservableList<Student> searchingStudents = FXCollections.observableArrayList();
        SearchOptionsComboBox searchOptionsComboBox = new SearchOptionsComboBox();

        RowConstraints row = new RowConstraints();
        row.setPercentHeight(75);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(5);
        RowConstraints row3 = new RowConstraints();
        row3.setPercentHeight(15);
        searchOptionsComboBox.getRoot().getRowConstraints().addAll(row, row2, row2, row3, row2);

        TableForm tableForm = new TableForm(searchingStudents);

        Alert alertSearchingStudents = new Alert(Alert.AlertType.NONE);
        alertSearchingStudents.getDialogPane().setContent(searchOptionsComboBox.getRoot());
        alertSearchingStudents.getButtonTypes().add(ButtonType.CLOSE);

        Button buttonSearchStudents = new Button(BUTTON_SEARCH_STUDENT);
        searchOptionsComboBox.getRoot().add(buttonSearchStudents, 0, 4);
        searchOptionsComboBox.getRoot().add(tableForm.getRootOfTableForm(), 0, 0);
        //searchOptionsComboBox.getRoot().setGridLinesVisible(true);

        buttonSearchStudents.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!validationCheck(searchOptionsComboBox.getSearchType(), searchOptionsComboBox.getFormsForSearch())) {
                    createErrorAlert().show();
                } else {
                    searchingStudents.clear();
                    tableForm.clearList();
                    List<Student> detectedStudents = searchStudents(searchOptionsComboBox.getSearchType(), searchOptionsComboBox.getFormsForSearch());
                    searchingStudents.addAll(detectedStudents);

                    mainForm.update(tableForm);
                }

            }
        });
        alertSearchingStudents.show();
    }

    private void openSearchingStudentsAlertForMenu(MainForm mainForm, SearchType searchType) {

        FormsForSearch formsForSearch = new FormsForSearch();
        ObservableList<Student> searchingStudents = FXCollections.observableArrayList();
        TableForm tableForm = new TableForm(searchingStudents);
        GridPane root = new GridPane();
        GridPane formRoot = new GridPane();
        Label searchLabel = new Label(SEARCH_PARAMETERS_STUDENT_LABEL);
        Separator separator = new Separator();

        Alert alertSearchingStudents = new Alert(Alert.AlertType.NONE);
        alertSearchingStudents.setTitle(getTitleForSearchingAlert(searchType));
        if (searchType == SearchType.SEARCH_BY_FULLNAME_AND_DAY_OF_BIRTH || searchType == SearchType.SEARCH_BY_FULLNAME_AND_DAY_OF_RECEIPT ||
                searchType == SearchType.SEARCH_BY_FULLNAME_AND_DAY_OF_GRADUATION)
            formRoot = formsForSearch.fullNameAndDay();
        if (searchType == SearchType.SEARCH_BY_YEAR_AND_DAY_OF_BIRTH || searchType == SearchType.SEARCH_BY_YEAR_AND_DAY_OF_RECEIPT ||
                searchType == SearchType.SEARCH_BY_YEAR_AND_DAY_OF_GRADUATION)
            formRoot = formsForSearch.yearAndDay();
        if (searchType == SearchType.SEARCH_BY_MONTH_AND_DAY_OF_BIRTH || searchType == SearchType.SEARCH_BY_MONTH_AND_DAY_OF_RECEIPT ||
                searchType == SearchType.SEARCH_BY_MONTH_AND_DAY_OF_GRADUATION)
            formRoot = formsForSearch.monthAndDay();

        alertSearchingStudents.getButtonTypes().add(ButtonType.CLOSE);
        Button buttonDeleteStudents = new Button(BUTTON_SEARCH_STUDENT);

        root.add(formRoot, 0, 3);
        root.add(tableForm.getRootOfTableForm(), 0, 0);
        root.add(searchLabel, 0, 2);
        root.add(separator, 0, 1);
        root.add(buttonDeleteStudents, 0, 4);

        alertSearchingStudents.getDialogPane().setContent(root);

        buttonDeleteStudents.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!validationCheck(searchType, formsForSearch)) {
                    createErrorAlert().show();
                } else {
                    searchingStudents.clear();
                    tableForm.clearList();
                    List<Student> detectedStudents = searchStudents(searchType, formsForSearch);
                    searchingStudents.addAll(detectedStudents);
                    mainForm.update(tableForm);
                }
            }
        });

        alertSearchingStudents.show();

    }

    private Boolean validationCheck(SearchType searchType, FormsForSearch formsForSearch) {

        if (searchType == SearchType.SEARCH_BY_FULLNAME_AND_DAY_OF_BIRTH || searchType == SearchType.SEARCH_BY_FULLNAME_AND_DAY_OF_RECEIPT ||
                searchType == SearchType.SEARCH_BY_FULLNAME_AND_DAY_OF_GRADUATION)
            return formsForSearch.getName().getText() != null && formsForSearch.getSurname().getText() != null &&
                    formsForSearch.getPatronymic().getText() != null && formsForSearch.getDay().getText() != null &&
                    new Scanner(formsForSearch.getDay().getText()).hasNextInt();
        if (searchType == SearchType.SEARCH_BY_YEAR_AND_DAY_OF_BIRTH || searchType == SearchType.SEARCH_BY_YEAR_AND_DAY_OF_RECEIPT ||
                searchType == SearchType.SEARCH_BY_YEAR_AND_DAY_OF_GRADUATION)
            return formsForSearch.getMaxLimitYear().getText() != null && formsForSearch.getMinLimitYear().getText() != null &&
                    formsForSearch.getDay().getText() != null && new Scanner(formsForSearch.getDay().getText()).hasNextInt() &&
                    new Scanner(formsForSearch.getMaxLimitYear().getText()).hasNextInt() &&
                    new Scanner(formsForSearch.getMinLimitYear().getText()).hasNextInt();

        if (searchType == SearchType.SEARCH_BY_MONTH_AND_DAY_OF_BIRTH || searchType == SearchType.SEARCH_BY_MONTH_AND_DAY_OF_RECEIPT ||
                searchType == SearchType.SEARCH_BY_MONTH_AND_DAY_OF_GRADUATION)
            return formsForSearch.getDay().getText() != null && formsForSearch.getMonth().getText() != null &&
                    new Scanner(formsForSearch.getDay().getText()).hasNextInt() &&
                    new Scanner(formsForSearch.getMonth().getText()).hasNextInt();
        return false;
    }

    private ObservableList<Student> searchStudents(SearchType searchType, FormsForSearch formsForSearch) {
        ObservableList<Student> detectedStudents = FXCollections.observableArrayList();

        switch (searchType) {
            case SEARCH_BY_FULLNAME_AND_DAY_OF_BIRTH:

                detectedStudents = searchStudentsController.searchByFullNameAndDayOfBirth(formsForSearch.getName().getText(),
                        formsForSearch.getSurname().getText(),
                        formsForSearch.getPatronymic().getText(),
                        Integer.parseInt(formsForSearch.getDay().getText()));
                break;

            case SEARCH_BY_FULLNAME_AND_DAY_OF_RECEIPT:
                detectedStudents = searchStudentsController.searchByFullNameAndDayOfReceipt
                        (formsForSearch.getName().getText(),
                                formsForSearch.getSurname().getText(),
                                formsForSearch.getPatronymic().getText(),
                                Integer.parseInt(formsForSearch.getDay().getText()));
                break;

            case SEARCH_BY_FULLNAME_AND_DAY_OF_GRADUATION:
                detectedStudents = searchStudentsController.searchByFullNameAndDayOfGraduation
                        (formsForSearch.getName().getText(),
                                formsForSearch.getSurname().getText(),
                                formsForSearch.getPatronymic().getText(),
                                Integer.parseInt(formsForSearch.getDay().getText()));
                break;

            case SEARCH_BY_YEAR_AND_DAY_OF_BIRTH:
                detectedStudents = searchStudentsController.searchByYearAndDayOfBirth
                        (Integer.parseInt(formsForSearch.getDay().getText()),
                                Integer.parseInt(formsForSearch.getMaxLimitYear().getText()),
                                Integer.parseInt(formsForSearch.getMinLimitYear().getText()));
                break;

            case SEARCH_BY_YEAR_AND_DAY_OF_RECEIPT:
                detectedStudents = searchStudentsController.searchByYearAndDayOfReceipt
                        (Integer.parseInt(formsForSearch.getDay().getText()),
                                Integer.parseInt(formsForSearch.getMaxLimitYear().getText()),
                                Integer.parseInt(formsForSearch.getMinLimitYear().getText()));
                break;

            case SEARCH_BY_YEAR_AND_DAY_OF_GRADUATION:
                detectedStudents = searchStudentsController.searchByYearAndDayOfGraduation
                        (Integer.parseInt(formsForSearch.getDay().getText()),
                                Integer.parseInt(formsForSearch.getMaxLimitYear().getText()),
                                Integer.parseInt(formsForSearch.getMinLimitYear().getText()));
                break;

            case SEARCH_BY_MONTH_AND_DAY_OF_BIRTH:
                detectedStudents = searchStudentsController.searchByMonthAndDayOfBirth
                        (Integer.parseInt(formsForSearch.getMonth().getText()),
                                Integer.parseInt(formsForSearch.getDay().getText()));
                break;

            case SEARCH_BY_MONTH_AND_DAY_OF_RECEIPT:
                detectedStudents = searchStudentsController.searchByMonthAndDayOfReceipt
                        (Integer.parseInt(formsForSearch.getMonth().getText()),
                                Integer.parseInt(formsForSearch.getDay().getText()));
                break;

            case SEARCH_BY_MONTH_AND_DAY_OF_GRADUATION:
                detectedStudents = searchStudentsController.searchByMonthAndDayOfGraduation
                        (Integer.parseInt(formsForSearch.getMonth().getText()),
                                Integer.parseInt(formsForSearch.getDay().getText()));
                break;

        }
        return detectedStudents;
    }

    private void openRemovingStudentsAlertWithComboBox(MainForm mainForm) {
        SearchOptionsComboBox searchOptionsComboBox = new SearchOptionsComboBox();

        Alert alertRemovingStudents = new Alert(Alert.AlertType.NONE);
        alertRemovingStudents.getDialogPane().setContent(searchOptionsComboBox.getRoot());
        alertRemovingStudents.getButtonTypes().add(ButtonType.CLOSE);

        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(100);
        searchOptionsComboBox.getRoot().getColumnConstraints().addAll(column);

        RowConstraints row = new RowConstraints();
        row.setPercentHeight(15);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(1);
        RowConstraints row3 = new RowConstraints();
        row3.setPercentHeight(54);
        searchOptionsComboBox.getRoot().getRowConstraints().addAll(row2, row, row, row3, row);
        // searchOptionsComboBox.getRoot().setGridLinesVisible(true);

        searchOptionsComboBox.getRoot().setMinWidth(500);

        Button buttonRemoveStudents = new Button(BUTTON_DELETE_STUDENT);
        searchOptionsComboBox.getRoot().add(buttonRemoveStudents, 0, 5);

        buttonRemoveStudents.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!validationCheck(searchOptionsComboBox.getSearchType(), searchOptionsComboBox.getFormsForSearch())) {
                    createErrorAlert().show();
                } else {

                    List<Student> removeStudents = searchStudents(searchOptionsComboBox.getSearchType(), searchOptionsComboBox.getFormsForSearch());
                    Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
                    informationAlert.setTitle("Информация");
                    if (removeStudents.size() == 0) {
                        informationAlert.setHeaderText("Удаление не было произведено");
                        informationAlert.setContentText("По данному запросу студентов найдено не было");
                        informationAlert.show();
                    } else {
                        informationAlert.setHeaderText("Удаление было произведено");

                        informationAlert.setContentText("Было удалено " + removeStudents.size() + " студентов");
                        informationAlert.show();
                    }
                    for (Student student : removeStudents)
                        changerStudentsData.removeStudent(student);
                    updateCloneData();
                    mainForm.update(tableForm);
                }
            }
        });

        alertRemovingStudents.show();

    }

    private String getTitleForRemovingAlert(SearchType searchType) {
        switch (searchType) {
            case SEARCH_BY_FULLNAME_AND_DAY_OF_BIRTH:
                return "Удаление по ФИО и дню рождения";
            case SEARCH_BY_MONTH_AND_DAY_OF_RECEIPT:
                return "Удаление по ФИО и дню поступления";
            case SEARCH_BY_MONTH_AND_DAY_OF_GRADUATION:
                return "Удаление по ФИО и дню окончания";
            case SEARCH_BY_YEAR_AND_DAY_OF_BIRTH:
                return "Удаление по году и дню рождения";
            case SEARCH_BY_YEAR_AND_DAY_OF_RECEIPT:
                return "Удаление по году и дню поступления";
            case SEARCH_BY_YEAR_AND_DAY_OF_GRADUATION:
                return "Удаление по году и дню окончания";
            case SEARCH_BY_MONTH_AND_DAY_OF_BIRTH:
                return "Удаление по месяцу и дню рождения";
            case SEARCH_BY_FULLNAME_AND_DAY_OF_RECEIPT:
                return "Удаление по месяцу и дню поступления";
            case SEARCH_BY_FULLNAME_AND_DAY_OF_GRADUATION:
                return "Удаление по месяцу и дню окончания";
        }
        return "";
    }

    private String getTitleForSearchingAlert(SearchType searchType) {
        switch (searchType) {
            case SEARCH_BY_FULLNAME_AND_DAY_OF_BIRTH:
                return "Поиск по ФИО и дню рождения";
            case SEARCH_BY_MONTH_AND_DAY_OF_RECEIPT:
                return "Поиск по ФИО и дню поступления";
            case SEARCH_BY_MONTH_AND_DAY_OF_GRADUATION:
                return "Поиск по ФИО и дню окончания";
            case SEARCH_BY_YEAR_AND_DAY_OF_BIRTH:
                return "Поиск по году и дню рождения";
            case SEARCH_BY_YEAR_AND_DAY_OF_RECEIPT:
                return "Поиск по году и дню поступления";
            case SEARCH_BY_YEAR_AND_DAY_OF_GRADUATION:
                return "Поиск по году и дню окончания";
            case SEARCH_BY_MONTH_AND_DAY_OF_BIRTH:
                return "Поиск по месяцу и дню рождения";
            case SEARCH_BY_FULLNAME_AND_DAY_OF_RECEIPT:
                return "Поиск по месяцу и дню поступления";
            case SEARCH_BY_FULLNAME_AND_DAY_OF_GRADUATION:
                return "Поиск по месяцу и дню окончания";
        }
        return "";
    }

    private void openRemovingStudentsAlertForMenu(MainForm mainForm, SearchType searchType) {

        FormsForSearch formsForSearch = new FormsForSearch();
        GridPane root = new GridPane();
        GridPane formRoot = new GridPane();

        Alert alertRemovingStudents = new Alert(Alert.AlertType.NONE);
        alertRemovingStudents.setTitle(getTitleForRemovingAlert(searchType));
        if (searchType == SearchType.SEARCH_BY_FULLNAME_AND_DAY_OF_BIRTH || searchType == SearchType.SEARCH_BY_FULLNAME_AND_DAY_OF_RECEIPT ||
                searchType == SearchType.SEARCH_BY_FULLNAME_AND_DAY_OF_GRADUATION)
            formRoot = formsForSearch.fullNameAndDay();
        if (searchType == SearchType.SEARCH_BY_YEAR_AND_DAY_OF_BIRTH || searchType == SearchType.SEARCH_BY_YEAR_AND_DAY_OF_RECEIPT ||
                searchType == SearchType.SEARCH_BY_YEAR_AND_DAY_OF_GRADUATION)
            formRoot = formsForSearch.yearAndDay();
        if (searchType == SearchType.SEARCH_BY_MONTH_AND_DAY_OF_BIRTH || searchType == SearchType.SEARCH_BY_MONTH_AND_DAY_OF_RECEIPT ||
                searchType == SearchType.SEARCH_BY_MONTH_AND_DAY_OF_GRADUATION)
            formRoot = formsForSearch.monthAndDay();

        alertRemovingStudents.getButtonTypes().add(ButtonType.CLOSE);
        Button buttonDeleteStudents = new Button(BUTTON_DELETE_STUDENT);

        root.add(buttonDeleteStudents, 0, 1);
        root.add(formRoot, 0, 0);
        alertRemovingStudents.getDialogPane().setContent(root);

        buttonDeleteStudents.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!validationCheck(searchType, formsForSearch)) {
                    createErrorAlert().show();
                } else {

                    List<Student> removeStudents = searchStudents(searchType, formsForSearch);
                    Alert informationAlert = new Alert(Alert.AlertType.INFORMATION);
                    informationAlert.setTitle("Информация");
                    if (removeStudents.size() == 0) {
                        informationAlert.setHeaderText("Удаление не было произведено");
                        informationAlert.setContentText("По данному запросу студентов найдено не было");
                        informationAlert.show();
                    } else {
                        informationAlert.setHeaderText("Удаление было произведено");

                        informationAlert.setContentText("Было удалено " + removeStudents.size() + " студентов");
                        informationAlert.show();
                    }
                    for (Student student : removeStudents)
                        changerStudentsData.removeStudent(student);
                    updateCloneData();
                    mainForm.update(tableForm);
                }
            }
        });

        alertRemovingStudents.show();

    }

    private File openFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML files", "*.xml"),
                new FileChooser.ExtensionFilter("All files", "*.*"));
        fileChooser.setTitle(OPEN_FILE_CHOOSER_TITLE);

        return fileChooser.showOpenDialog(primaryStage);
    }

    private File saveFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML files", "*.xml"),
                new FileChooser.ExtensionFilter("All files", "*.*"));
        fileChooser.setTitle(SAVE_FILE_CHOOSER_TITLE);
        return fileChooser.showSaveDialog(primaryStage);
    }

    private MenuBar initMenu(MainForm mainForm) {
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(createFileMenu(mainForm), createEditMenu(mainForm));
        return menuBar;

    }

    private Menu createFileMenu(MainForm mainForm) {
        Menu fileMenu = new Menu(FILE_MENU);

        MenuItem newItem = new MenuItem(MENU_ITEM_NEW);
        MenuItem openItem = new MenuItem(MENU_ITEM_OPEN);
        MenuItem saveItem = new MenuItem(MENU_ITEM_SAVE);
        MenuItem generateItem = new MenuItem(MENU_ITEM_GENERATE);
        SeparatorMenuItem separator1 = new SeparatorMenuItem();
        SeparatorMenuItem separator2 = new SeparatorMenuItem();
        SeparatorMenuItem separator3 = new SeparatorMenuItem();

        fileMenu.getItems().addAll(newItem, separator1, openItem, separator2, saveItem, separator3, generateItem);

        newItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                changerStudentsData.newFile();
                updateCloneData();
                mainForm.update(tableForm);
            }
        });

        openItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File openFile = openFileChooser();

                if (openFile != null) {
                    try {
                        fileLoader.readFromXMLFile(openFile.getAbsolutePath());
                    } catch (SAXException | ParserConfigurationException | IOException e) {
                        e.printStackTrace();
                    }
                    updateCloneData();
                    mainForm.update(tableForm);
                }
            }
        });

        saveItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File saveFile = saveFileChooser();
                try {
                    if (saveFile != null)
                        fileLoader.writeToXMLFile(saveFile.getAbsolutePath());
                } catch (ParserConfigurationException | TransformerException e) {
                    e.printStackTrace();
                }
            }
        });

        generateItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    changerStudentsData.generate();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                updateCloneData();
                mainForm.update(tableForm);
            }
        });
        return fileMenu;
    }

    private Menu createEditMenu(MainForm mainForm) {
        Menu editMenu = new Menu(EDIT_MENU);

        MenuItem addItem = new MenuItem(MENU_ITEM_ADD);

        Menu deleteItem = new Menu(MENU_ITEM_DELETE_);
        MenuItem deleteByFullNameAndDayOfBirth = new MenuItem(DELETE_BY_FULL_NAME_AND_DAY_OF_BIRTH);
        MenuItem deleteByYearAndDayOfBirth = new MenuItem(DELETE_BY_YEAR_AND_DAY_OF_BIRTH);
        MenuItem deleteByMonthAndDayOfBirth = new MenuItem(DELETE_BY_MONTH_AND_DAY_OF_BIRTH);
        MenuItem deleteByFullNameAndDayOfReceipt = new MenuItem(DELETE_BY_FULL_NAME_AND_DAY_OF_RECEIPT);
        MenuItem deleteByYearAndDayOfReceipt = new MenuItem(DELETE_BY_YEAR_AND_DAY_OF_RECEIPT);
        MenuItem deleteByMonthAndDayOfReceipt = new MenuItem(DELETE_BY_MONTH_AND_DAY_OF_RECEIPT);
        MenuItem deleteByFullNameAndDayOfGraduation = new MenuItem(DELETE_BY_FULL_NAME_AND_DAY_OF_GRADUATION);
        MenuItem deleteByYearAndDayOfGraduation = new MenuItem(DELETE_BY_YEAR_AND_DAY_OF_GRADUATION);
        MenuItem deleteByMonthAndDayOfGraduation = new MenuItem(DELETE_BY_MONTH_AND_DAY_OF_GRADUATION);

        Menu searchItem = new Menu(MENU_ITEM_SEARCH);
        MenuItem searchByFullNameAndDayOfBirth = new MenuItem(SEARCH_BY_FULL_NAME_AND_DAY_OF_BIRTH);
        MenuItem searchByYearAndDayOfBirth = new MenuItem(SEARCH_BY_YEAR_AND_DAY_OF_BIRTH);
        MenuItem searchByMonthAndDayOfBirth = new MenuItem(SEARCH_BY_MONTH_AND_DAY_OF_BIRTH);
        MenuItem searchByFullNameAndDayOfReceipt = new MenuItem(SEARCH_BY_FULL_NAME_AND_DAY_OF_RECEIPT);
        MenuItem searchByYearAndDayOfReceipt = new MenuItem(SEARCH_BY_YEAR_AND_DAY_OF_RECEIPT);
        MenuItem searchByMonthAndDayOfReceipt = new MenuItem(SEARCH_BY_MONTH_AND_DAY_OF_RECEIPT);
        MenuItem searchByFullNameAndDayOfGraduation = new MenuItem(SEARCH_BY_FULL_NAME_AND_DAY_OF_GRADUATION);
        MenuItem searchByYearAndDayOfGraduation = new MenuItem(SEARCH_BY_YEAR_AND_DAY_OF_GRADUATION);
        MenuItem searchByMonthAndDayOfGraduation = new MenuItem(SEARCH_BY_MONTH_AND_DAY_OF_GRADUATION);

        SeparatorMenuItem separator1 = new SeparatorMenuItem();
        SeparatorMenuItem separator2 = new SeparatorMenuItem();

        editMenu.getItems().addAll(addItem, separator1, deleteItem, separator2, searchItem);
        deleteItem.getItems().addAll(deleteByFullNameAndDayOfBirth, deleteByYearAndDayOfBirth, deleteByMonthAndDayOfBirth,
                deleteByFullNameAndDayOfReceipt, deleteByYearAndDayOfReceipt, deleteByMonthAndDayOfReceipt,
                deleteByFullNameAndDayOfGraduation, deleteByYearAndDayOfGraduation, deleteByMonthAndDayOfGraduation);

        searchItem.getItems().addAll(searchByFullNameAndDayOfBirth, searchByYearAndDayOfBirth, searchByMonthAndDayOfBirth,
                searchByFullNameAndDayOfReceipt, searchByYearAndDayOfReceipt, searchByMonthAndDayOfReceipt,
                searchByFullNameAndDayOfGraduation, searchByYearAndDayOfGraduation, searchByMonthAndDayOfGraduation);

        addItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openAddingStudentsAlert(mainForm);
            }
        });

        deleteByFullNameAndDayOfBirth.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openRemovingStudentsAlertForMenu(mainForm, SearchType.SEARCH_BY_FULLNAME_AND_DAY_OF_BIRTH);
            }
        });

        deleteByFullNameAndDayOfReceipt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openRemovingStudentsAlertForMenu(mainForm, SearchType.SEARCH_BY_FULLNAME_AND_DAY_OF_RECEIPT);
            }
        });
        deleteByFullNameAndDayOfGraduation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openRemovingStudentsAlertForMenu(mainForm, SearchType.SEARCH_BY_FULLNAME_AND_DAY_OF_GRADUATION);
            }
        });
        deleteByYearAndDayOfBirth.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openRemovingStudentsAlertForMenu(mainForm, SearchType.SEARCH_BY_YEAR_AND_DAY_OF_BIRTH);
            }
        });
        deleteByYearAndDayOfReceipt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openRemovingStudentsAlertForMenu(mainForm, SearchType.SEARCH_BY_YEAR_AND_DAY_OF_RECEIPT);
            }
        });
        deleteByYearAndDayOfGraduation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openRemovingStudentsAlertForMenu(mainForm, SearchType.SEARCH_BY_YEAR_AND_DAY_OF_GRADUATION);
            }
        });
        deleteByMonthAndDayOfBirth.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openRemovingStudentsAlertForMenu(mainForm, SearchType.SEARCH_BY_MONTH_AND_DAY_OF_BIRTH);
            }
        });
        deleteByMonthAndDayOfReceipt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openRemovingStudentsAlertForMenu(mainForm, SearchType.SEARCH_BY_MONTH_AND_DAY_OF_RECEIPT);
            }
        });
        deleteByMonthAndDayOfGraduation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openRemovingStudentsAlertForMenu(mainForm, SearchType.SEARCH_BY_MONTH_AND_DAY_OF_GRADUATION);
            }
        });

        searchByFullNameAndDayOfBirth.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openSearchingStudentsAlertForMenu(mainForm, SearchType.SEARCH_BY_FULLNAME_AND_DAY_OF_BIRTH);
            }
        });

        searchByFullNameAndDayOfReceipt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openSearchingStudentsAlertForMenu(mainForm, SearchType.SEARCH_BY_FULLNAME_AND_DAY_OF_RECEIPT);
            }
        });
        searchByFullNameAndDayOfGraduation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openSearchingStudentsAlertForMenu(mainForm, SearchType.SEARCH_BY_FULLNAME_AND_DAY_OF_GRADUATION);
            }
        });
        searchByYearAndDayOfBirth.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openSearchingStudentsAlertForMenu(mainForm, SearchType.SEARCH_BY_YEAR_AND_DAY_OF_BIRTH);
            }
        });
        searchByYearAndDayOfReceipt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openSearchingStudentsAlertForMenu(mainForm, SearchType.SEARCH_BY_YEAR_AND_DAY_OF_RECEIPT);
            }
        });
        searchByYearAndDayOfGraduation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openSearchingStudentsAlertForMenu(mainForm, SearchType.SEARCH_BY_YEAR_AND_DAY_OF_GRADUATION);
            }
        });
        searchByMonthAndDayOfBirth.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openSearchingStudentsAlertForMenu(mainForm, SearchType.SEARCH_BY_MONTH_AND_DAY_OF_BIRTH);
            }
        });
        searchByMonthAndDayOfReceipt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openSearchingStudentsAlertForMenu(mainForm, SearchType.SEARCH_BY_MONTH_AND_DAY_OF_RECEIPT);
            }
        });
        searchByMonthAndDayOfGraduation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openSearchingStudentsAlertForMenu(mainForm, SearchType.SEARCH_BY_MONTH_AND_DAY_OF_GRADUATION);
            }
        });
        return editMenu;
    }

    private ToolBar createToolBar(MainForm mainForm) {
        ToolBar toolBar = new ToolBar();

        Image newFile = new Image(new File("new.png").toURI().toString(), 36,
                36, true, true);
        Image openFile = new Image(new File("open.png").toURI().toString(), 36,
                36, true, true);
        Image saveFile = new Image(new File("save.png").toURI().toString(), 36,
                36, true, true);
        Image generate = new Image(new File("generate.png").toURI().toString(), 36,
                36, true, true);
        Image add = new Image(new File("add.png").toURI().toString(), 36,
                36, true, true);
        Image delete = new Image(new File("delete.png").toURI().toString(), 36,
                36, true, true);
        Image search = new Image(new File("search.png").toURI().toString(), 36,
                36, true, true);

        ImageView newFileImage = new ImageView(newFile);
        ImageView openFileImage = new ImageView(openFile);
        ImageView saveFileImage = new ImageView(saveFile);
        ImageView generateImage = new ImageView(generate);
        ImageView addImage = new ImageView(add);
        ImageView deleteImage = new ImageView(delete);
        ImageView searchImage = new ImageView(search);

        Button buttonOpenAddingStudentsAlert = new Button("", addImage);
        Button buttonOpenRemovingStudentsAlert = new Button("", deleteImage);
        Button buttonOpenSearchingStudentsAlert = new Button("", searchImage);
        Button buttonGenerateStudents = new Button("", generateImage);
        Button buttonSaveFile = new Button("", saveFileImage);
        Button buttonNewFile = new Button("", newFileImage);
        Button buttonOpenFile = new Button("", openFileImage);

        toolBar.getItems().addAll(buttonNewFile, buttonOpenFile, buttonSaveFile, buttonGenerateStudents,
                buttonOpenAddingStudentsAlert, buttonOpenRemovingStudentsAlert, buttonOpenSearchingStudentsAlert);
        toolBar.setOrientation(Orientation.VERTICAL);



        buttonOpenAddingStudentsAlert.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                openAddingStudentsAlert(MainForm.this);
            }
        });

        buttonOpenSearchingStudentsAlert.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openSearchingStudentsAlertWithComboBox(MainForm.this);
            }
        });

        buttonOpenRemovingStudentsAlert.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openRemovingStudentsAlertWithComboBox(MainForm.this);
            }
        });

        buttonGenerateStudents.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    changerStudentsData.generate();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                updateCloneData();
                mainForm.update(tableForm);
            }
        });

        buttonOpenFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File openFile = openFileChooser();
                if (openFile != null) {
                    try {
                        fileLoader.readFromXMLFile(openFile.getAbsolutePath());
                    } catch (SAXException | ParserConfigurationException | IOException e) {
                        e.printStackTrace();
                    }
                    updateCloneData();
                    mainForm.update(tableForm);
                }
            }
        });

        buttonSaveFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File saveFile = saveFileChooser();
                try {
                    if (saveFile != null)
                        fileLoader.writeToXMLFile(saveFile.getAbsolutePath());
                } catch (ParserConfigurationException | TransformerException e) {
                    e.printStackTrace();
                }
            }
        });
        buttonNewFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                changerStudentsData.newFile();
                updateCloneData();
                mainForm.update(tableForm);
            }
        });
        return toolBar;
    }

    public GridPane getRoot() {
        return root;
    }

}
