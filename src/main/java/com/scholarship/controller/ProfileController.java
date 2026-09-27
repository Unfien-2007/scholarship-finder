package com.scholarship.controller;

import com.scholarship.Main;
import com.scholarship.dao.ScholarshipDAO;
import com.scholarship.dao.StudentDAO;
import com.scholarship.exception.DatabaseException;
import com.scholarship.exception.InvalidInputException;
import com.scholarship.model.Scholarship;
import com.scholarship.model.Student;
import com.scholarship.service.EligibilityChecker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private TextField ageField;
    @FXML
    private ComboBox<Integer> gradeLevelCombo;
    @FXML
    private TextField incomeField;
    @FXML
    private CheckBox disabilityCheck;
    @FXML
    private Label errorLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int grade = 7; grade <= 12; grade++) {
            gradeLevelCombo.getItems().add(grade);
        }
        gradeLevelCombo.setValue(11);
    }

    @FXML
    private void handleSubmit(ActionEvent event) {
        try {
            Student student = buildStudent();

            StudentDAO studentDAO = new StudentDAO();
            int studentId = studentDAO.saveStudent(student);
            student.setStudentId(studentId);

            ScholarshipDAO scholarshipDAO = new ScholarshipDAO();
            List<Scholarship> all = scholarshipDAO.findAll();

            EligibilityChecker checker = new EligibilityChecker();
            List<Scholarship> matches = checker.findMatches(all, student);

            Main.showResults(student, matches);
        } catch (InvalidInputException e) {
            errorLabel.setText(e.getMessage());
        } catch (DatabaseException e) {
            errorLabel.setText("Database error: " + e.getMessage());
        } catch (Exception e) {
            errorLabel.setText("Unexpected error: " + e.getMessage());
        }
    }

    @FXML
    private void handleBack(ActionEvent event) throws Exception {
        Main.showMain();
    }

    private Student buildStudent() throws InvalidInputException {
        String name = nameField.getText() == null ? "" : nameField.getText().trim();
        if (name.isEmpty()) {
            throw new InvalidInputException("Full name is required.");
        }

        int age;
        try {
            age = Integer.parseInt(ageField.getText().trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Age must be a number.");
        }
        if (age < 1 || age > 100) {
            throw new InvalidInputException("Age must be between 1 and 100.");
        }

        Integer grade = gradeLevelCombo.getValue();
        if (grade == null) {
            throw new InvalidInputException("Please select a grade level.");
        }

        double income;
        try {
            income = Double.parseDouble(incomeField.getText().trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Family income must be a number.");
        }
        if (income < 0) {
            throw new InvalidInputException("Family income cannot be negative.");
        }

        boolean disability = disabilityCheck.isSelected();

        return new Student(name, age, grade, income, disability);
    }
}
