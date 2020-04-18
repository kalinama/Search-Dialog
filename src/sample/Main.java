package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controller.ChangerStudentsData;
import sample.controller.FileLoader;
import sample.controller.SearchStudentsController;
import sample.model.StudentsData;
import sample.view.MainForm;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        StudentsData studentsData = new StudentsData();
        ChangerStudentsData changerStudentsData = new ChangerStudentsData(studentsData);
        SearchStudentsController searchStudentsController = new SearchStudentsController(studentsData);
        FileLoader fileLoader = new FileLoader(studentsData);
        MainForm mainForm = new MainForm(changerStudentsData, searchStudentsController, fileLoader, primaryStage);


        // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("");


        primaryStage.setScene(new Scene(mainForm.getRoot(), 871, 550));


        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
