package com.scholarship;

import com.scholarship.controller.ResultsController;
import com.scholarship.model.Scholarship;
import com.scholarship.model.Student;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        showMain();
        primaryStage.show();
    }

    public static void showMain() throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/com/scholarship/main.fxml"));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Scholarship Finder");
    }

    public static void showProfile() throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/com/scholarship/profile.fxml"));
        Parent root = loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Student Profile");
    }

    public static void showResults(Student student, List<Scholarship> matches) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/com/scholarship/results.fxml"));
        Parent root = loader.load();
        ResultsController controller = loader.getController();
        controller.setResults(student, matches);
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Matching Scholarships");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
