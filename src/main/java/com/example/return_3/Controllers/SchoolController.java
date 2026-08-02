package com.example.return_3.Controllers;

import com.example.return_3.main.Game;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;

import java.io.IOException;

import static com.example.return_3.main.Game.primaryStage;

public class SchoolController {

    public Button mathButton;
    public Button histryButton;
    public Button geographybutton;
    public Button englishButton;

    @FXML
    public Button backButton;

    @FXML
    void back(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Confirmation");

        alert.setHeaderText("You are about to exit!");
        alert.setContentText("Are you sure you want to exit?");

        // Add "Yes" and "No" buttons to the confirmation dialog
        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        // Custom CSS for the dialog
        alert.getDialogPane().getStylesheets().add(
                Game.class.getResource("/com/example/return_3/alert.css").toExternalForm());
        alert.initStyle(StageStyle.TRANSPARENT); // Transparent style for a sleek look

        // Handle button actions
        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == buttonTypeYes) {
                Game.gameInstance.keyHandler.setBooleanAll(false);
                Game.showGameScene();
            }
        });



//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Exit");
//        alert.setHeaderText("You are about to exit");
//        alert.setContentText("Do you want to leave school?");
//
//        if (alert.showAndWait().get() == ButtonType.OK) {
//            Game.gameInstance.keyHandler.setBooleanAll(false);
//            Game.showGameScene();
//        }


//        Game.gameInstance.keyHandler.setBooleanAll(false);
//        Game.showGameScene();
    }

    @FXML
    void goToMath(ActionEvent event) throws Exception {
       Game.gameInstance.showMathScene();
    }

    public void goTohistory(ActionEvent event) throws IOException {
        Game.gameInstance.showHistoryScene();
    }

    public void goToGeography(ActionEvent event) throws IOException {
        Game.gameInstance.showGeographyScene();
    }

    public void goToEnlish(ActionEvent event) throws IOException {
       Game.gameInstance.showEnglishScene();
    }


}
