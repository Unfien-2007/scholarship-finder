package com.scholarship.controller;

import com.scholarship.Main;
import com.scholarship.model.Scholarship;
import com.scholarship.model.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ResultsController implements Initializable {

    @FXML
    private Label headerLabel;
    @FXML
    private TableView<Scholarship> scholarshipTable;
    @FXML
    private TableColumn<Scholarship, String> nameColumn;
    @FXML
    private TableColumn<Scholarship, String> providerColumn;
    @FXML
    private TableColumn<Scholarship, String> typeColumn;
    @FXML
    private TableColumn<Scholarship, LocalDate> deadlineColumn;
    @FXML
    private TextArea detailArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        providerColumn.setCellValueFactory(new PropertyValueFactory<>("provider"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        deadlineColumn.setCellValueFactory(new PropertyValueFactory<>("deadline"));

        scholarshipTable.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                detailArea.setText(newValue.getDescription() + "\n\nBenefits: " + newValue.getBenefits());
            }
        });
    }

    public void setResults(Student student, List<Scholarship> matches) {
        headerLabel.setText("Scholarships for " + student.getFullName() + " (Grade " + student.getGradeLevel() + ")");
        scholarshipTable.getItems().setAll(matches);
        if (matches.isEmpty()) {
            detailArea.setText("No matching scholarships found for your profile.");
        }
    }

    @FXML
    private void handleBack(ActionEvent event) throws Exception {
        Main.showProfile();
    }
}
