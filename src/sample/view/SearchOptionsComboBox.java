package sample.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import sample.SearchType;


public class SearchOptionsComboBox {

    private static final String SEARCH_PARAMETERS_STUDENT_LABEL = "Введите данные для поиска: ";
    private final static String SEARCH_BY_FULL_NAME_AND_DAY_OF_BIRTH = "Поиск по ФИО и дню рождения";
    private final static String SEARCH_BY_YEAR_AND_DAY_OF_BIRTH = "Поиск по году и дню рождения";
    private final static String SEARCH_BY_MONTH_AND_DAY_OF_BIRTH = "Поиск по месяцу и дню рождения";
    private final static String SEARCH_BY_FULL_NAME_AND_DAY_OF_RECEIPT = "Поиск по ФИО и дню поступления";
    private final static String SEARCH_BY_YEAR_AND_DAY_OF_RECEIPT = "Поиск по году и дню поступления";
    private final static String SEARCH_BY_MONTH_AND_DAY_OF_RECEIPT = "Поиск по месяцу и дню поступления";
    private final static String SEARCH_BY_FULL_NAME_AND_DAY_OF_GRADUATION = "Поиск по ФИО и дню окончания";
    private final static String SEARCH_BY_YEAR_AND_DAY_OF_GRADUATION = "Поиск по году и дню окончания";
    private final static String SEARCH_BY_MONTH_AND_DAY_OF_GRADUATION = "Поиск по месяцу и дню окончания";

    private final static int COLUMN_FOR_FORM_FOR_SEARCH = 0;
    private final static int ROW_FOR_FORM_FOR_SEARCH = 3;

    private GridPane rootOfPreviousFormForSearch;

   private GridPane root;
   private ComboBox<String> comboBoxSearch;
   private FormsForSearch formsForSearch;


    SearchOptionsComboBox(  ) {
        searchingStudentsFormInit();
    }

    private void searchingStudentsFormInit()
    {
        root = new GridPane();
        Label searchStudentsLabel = new Label(SEARCH_PARAMETERS_STUDENT_LABEL);

        ObservableList<String> optionListOfSearch = FXCollections.observableArrayList(SEARCH_BY_FULL_NAME_AND_DAY_OF_BIRTH,
                SEARCH_BY_YEAR_AND_DAY_OF_BIRTH, SEARCH_BY_MONTH_AND_DAY_OF_BIRTH, SEARCH_BY_FULL_NAME_AND_DAY_OF_RECEIPT,
                SEARCH_BY_YEAR_AND_DAY_OF_RECEIPT, SEARCH_BY_MONTH_AND_DAY_OF_RECEIPT, SEARCH_BY_FULL_NAME_AND_DAY_OF_GRADUATION,
                SEARCH_BY_YEAR_AND_DAY_OF_GRADUATION, SEARCH_BY_MONTH_AND_DAY_OF_GRADUATION);

        comboBoxSearch = new ComboBox<>(optionListOfSearch);

        rootOfPreviousFormForSearch = new GridPane();
        root.add(comboBoxSearch, 0, 1);
        root.add(searchStudentsLabel,0,2);

        comboBoxSearch.setOnAction(new EventHandler<ActionEvent>() {
                                       @Override
                                       public void handle(ActionEvent event) {

                                           formsForSearch = new FormsForSearch();

                                           GridPane rootOfFormForSearch = new GridPane();

                                           if (comboBoxSearch.getValue().equals(SEARCH_BY_FULL_NAME_AND_DAY_OF_BIRTH) ||
                                                   comboBoxSearch.getValue().equals(SEARCH_BY_FULL_NAME_AND_DAY_OF_RECEIPT) ||
                                                   comboBoxSearch.getValue().equals(SEARCH_BY_FULL_NAME_AND_DAY_OF_GRADUATION))
                                               rootOfFormForSearch = formsForSearch.fullNameAndDay();

                                           if (comboBoxSearch.getValue().equals(SEARCH_BY_YEAR_AND_DAY_OF_BIRTH) ||
                                                   comboBoxSearch.getValue().equals(SEARCH_BY_YEAR_AND_DAY_OF_RECEIPT) ||
                                                   comboBoxSearch.getValue().equals(SEARCH_BY_YEAR_AND_DAY_OF_GRADUATION))
                                               rootOfFormForSearch = formsForSearch.yearAndDay();

                                           if (comboBoxSearch.getValue().equals(SEARCH_BY_MONTH_AND_DAY_OF_BIRTH) ||
                                                   comboBoxSearch.getValue().equals(SEARCH_BY_MONTH_AND_DAY_OF_RECEIPT) ||
                                                   comboBoxSearch.getValue().equals(SEARCH_BY_MONTH_AND_DAY_OF_GRADUATION)
                                           )
                                               rootOfFormForSearch = formsForSearch.monthAndDay();


                                           root.getChildren().remove(rootOfPreviousFormForSearch);
                                           root.add(rootOfFormForSearch, COLUMN_FOR_FORM_FOR_SEARCH, ROW_FOR_FORM_FOR_SEARCH);

                                           rootOfPreviousFormForSearch = rootOfFormForSearch;

                                       }
                                   });

    }

    SearchType getSearchType()
    {
        SearchType searchType = SearchType.EMPTY;

        if (comboBoxSearch.getValue().equals(SEARCH_BY_FULL_NAME_AND_DAY_OF_BIRTH))
            return SearchType.SEARCH_BY_FULLNAME_AND_DAY_OF_BIRTH;

        if (comboBoxSearch.getValue().equals(SEARCH_BY_YEAR_AND_DAY_OF_BIRTH))
            return SearchType.SEARCH_BY_YEAR_AND_DAY_OF_BIRTH;

        if (comboBoxSearch.getValue().equals(SEARCH_BY_MONTH_AND_DAY_OF_BIRTH))
            return SearchType.SEARCH_BY_MONTH_AND_DAY_OF_BIRTH;

        if (comboBoxSearch.getValue().equals(SEARCH_BY_FULL_NAME_AND_DAY_OF_RECEIPT))
            return SearchType.SEARCH_BY_FULLNAME_AND_DAY_OF_RECEIPT;

        if (comboBoxSearch.getValue().equals(SEARCH_BY_YEAR_AND_DAY_OF_RECEIPT))
            return SearchType.SEARCH_BY_YEAR_AND_DAY_OF_RECEIPT;

        if (comboBoxSearch.getValue().equals(SEARCH_BY_MONTH_AND_DAY_OF_RECEIPT))
            return SearchType.SEARCH_BY_MONTH_AND_DAY_OF_RECEIPT;

        if (comboBoxSearch.getValue().equals(SEARCH_BY_FULL_NAME_AND_DAY_OF_GRADUATION))
            return SearchType.SEARCH_BY_FULLNAME_AND_DAY_OF_GRADUATION;

        if (comboBoxSearch.getValue().equals(SEARCH_BY_YEAR_AND_DAY_OF_GRADUATION))
            return SearchType.SEARCH_BY_YEAR_AND_DAY_OF_GRADUATION;

        if (comboBoxSearch.getValue().equals(SEARCH_BY_MONTH_AND_DAY_OF_GRADUATION))
            return SearchType.SEARCH_BY_MONTH_AND_DAY_OF_GRADUATION;


        return searchType;
    }

    GridPane getRoot() {
        return root;
    }

    FormsForSearch getFormsForSearch() {
        return formsForSearch;
    }

}
