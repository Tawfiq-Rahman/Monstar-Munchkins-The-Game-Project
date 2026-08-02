package com.example.return_3.Controllers;

import com.example.return_3.main.Game;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AlgebraTestPage1Controller {
    public TextField q1Option1;
    Game game;
    public Button testExitBtn1;
    public Button q1Option1Btn;
    public Button q1Option4Btn;
    public Button q1Option3Btn;
    public Button q1Option2;
    public Button q2Option1Btn;
    public Button q2Option2Btn;
    public Button q2Option3Btn;
    public Button q2Option4Btn;
    public Button q3Option1Btn;
    public Button q3Option2Btn;
    public Button q3Option3Btn;
    public Button q3Option4Btn;
    public Button testNextBtn1;

    public void exitToAlgebraPage7(ActionEvent event) throws IOException {
        Game.gameInstance.showAlgebraPage7();
    }

    public void q1Option1(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q1Option1Btn.setStyle("-fx-background-color: #33BF5E");

        // Disable the other buttons
        q1Option2.setDisable(true);
        q1Option3Btn.setDisable(true);
        q1Option4Btn.setDisable(true);
    }


    public void q1Option4(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q1Option4Btn.setStyle("-fx-background-color: #FF3333");
        q1Option1Btn.setStyle("-fx-background-color: #33BF5E");

        // Disable the other buttons
        q1Option1Btn.setDisable(true);
        q1Option2.setDisable(true);
        q1Option3Btn.setDisable(true);
    }

    public void q1Option3(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q1Option3Btn.setStyle("-fx-background-color: #FF3333");
        q1Option1Btn.setStyle("-fx-background-color: #33BF5E");

        // Disable the other buttons
        q1Option1Btn.setDisable(true);
        q1Option2.setDisable(true);
        q1Option4Btn.setDisable(true);
    }

    public void q1Option2(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q1Option2.setStyle("-fx-background-color: #FF3333");
        q1Option1Btn.setStyle("-fx-background-color: #33BF5E");

        // Disable the other buttons
        q1Option1Btn.setDisable(true);
        q1Option2.setDisable(true);
        q1Option3Btn.setDisable(true);
    }

    public void q2Option1(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q2Option1Btn.setStyle("-fx-background-color: #FF3333");
        q2Option2Btn.setStyle("-fx-background-color: #33BF5E");

        // Disable the other buttons
        q2Option1Btn.setDisable(true);
        q2Option4Btn.setDisable(true);
        q2Option3Btn.setDisable(true);
    }

    public void q2Option2(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q2Option2Btn.setStyle("-fx-background-color: #33BF5E");

        // Disable the other buttons
        q2Option1Btn.setDisable(true);
        q2Option3Btn.setDisable(true);
        q2Option4Btn.setDisable(true);
    }

    public void q2Option3(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q2Option3Btn.setStyle("-fx-background-color: #FF3333");
        q2Option2Btn.setStyle("-fx-background-color: #33BF5E");

        // Disable the other buttons
        q2Option1Btn.setDisable(true);
        q2Option4Btn.setDisable(true);
        q2Option2Btn.setDisable(true);
    }

    public void q2Option4(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q2Option4Btn.setStyle("-fx-background-color: #FF3333");
        q2Option2Btn.setStyle("-fx-background-color: #33BF5E");

        // Disable the other buttons
        q2Option1Btn.setDisable(true);
        q2Option2Btn.setDisable(true);
        q2Option3Btn.setDisable(true);
    }

    public void q3Option1(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q3Option1Btn.setStyle("-fx-background-color: #FF3333");
        q3Option3Btn.setStyle("-fx-background-color: #33BF5E");

        // Disable the other buttons
        q3Option4Btn.setDisable(true);
        q3Option2Btn.setDisable(true);
        q3Option3Btn.setDisable(true);
    }

    public void q3Option2(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q3Option2Btn.setStyle("-fx-background-color: #FF3333");
        q3Option3Btn.setStyle("-fx-background-color: #33BF5E");

        // Disable the other buttons
        q3Option4Btn.setDisable(true);
        q3Option1Btn.setDisable(true);
        q3Option3Btn.setDisable(true);
    }

    public void q3Option3(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q3Option3Btn.setStyle("-fx-background-color: #33BF5E");

        // Disable the other buttons
        q3Option1Btn.setDisable(true);
        q3Option2Btn.setDisable(true);
        q3Option4Btn.setDisable(true);
    }

    public void q3Option4(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q3Option4Btn.setStyle("-fx-background-color: #FF3333");
        q3Option3Btn.setStyle("-fx-background-color: #33BF5E");

        // Disable the other buttons
        q3Option1Btn.setDisable(true);
        q3Option2Btn.setDisable(true);
        q3Option3Btn.setDisable(true);
    }

    public void textNext1(ActionEvent event) throws IOException {
        Game.gameInstance.showAlgebraTestPage2();
    }

}
