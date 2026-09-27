package com.scholarship.controller;

import com.scholarship.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainController {

    @FXML
    private void handleStart(ActionEvent event) throws Exception {
        Main.showProfile();
    }
}
