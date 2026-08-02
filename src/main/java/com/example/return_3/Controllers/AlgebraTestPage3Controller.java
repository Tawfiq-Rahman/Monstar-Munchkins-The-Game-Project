package com.example.return_3.Controllers;

import com.example.return_3.main.Game;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class AlgebraTestPage3Controller {

    public Button testExitBtn3;
    public Button q7Option1Btn;
    public Button q7Option4Btn;
    public Button q7Option3Btn;
    public Button q7Option2Btn;
    public Button q8Option1Btn;
    public Button q8Option2Btn;
    public Button q8Option3Btn;
    public Button q8Option4Btn;
    public Button q9Option1Btn;
    public Button q9Option2Btn;
    public Button q9Option3Btn;
    public Button q9Option4Btn;
    public Button testNextBtn3;


    public void exitToAlgebraPage7(ActionEvent event) throws IOException {
        Game.gameInstance.showAlgebraPage7();
    }

    public void q7Option1(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q7Option1Btn.setStyle("-fx-background-color: #33BF5E");

        // Disable the other buttons
        q7Option1Btn.setDisable(true);
        q7Option4Btn.setDisable(true);
        q7Option2Btn.setDisable(true);
    }

    public void q7Option4(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q7Option1Btn.setStyle("-fx-background-color: #33BF5E");
        q7Option4Btn.setStyle("-fx-background-color: #FF3333");

        // Disable the other buttons
        q7Option1Btn.setDisable(true);
        q7Option3Btn.setDisable(true);
        q7Option2Btn.setDisable(true);
    }

    public void q7Option3(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q7Option1Btn.setStyle("-fx-background-color: #33BF5E");
        q7Option3Btn.setStyle("-fx-background-color: #FF3333");

        // Disable the other buttons
        q7Option1Btn.setDisable(true);
        q7Option4Btn.setDisable(true);
        q7Option2Btn.setDisable(true);
    }

    public void q7Option2(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q7Option1Btn.setStyle("-fx-background-color: #33BF5E");
        q7Option2Btn.setStyle("-fx-background-color: #FF3333");

        // Disable the other buttons
        q7Option1Btn.setDisable(true);
        q7Option3Btn.setDisable(true);
        q7Option4Btn.setDisable(true);
    }

    public void q8Option1(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q8Option3Btn.setStyle("-fx-background-color: #33BF5E");
        q8Option1Btn.setStyle("-fx-background-color: #FF3333");

        // Disable the other buttons
        q8Option3Btn.setDisable(true);
        q8Option2Btn.setDisable(true);
        q8Option4Btn.setDisable(true);
    }

    public void q8Option2(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q8Option3Btn.setStyle("-fx-background-color: #33BF5E");
        q8Option2Btn.setStyle("-fx-background-color: #FF3333");

        // Disable the other buttons
        q8Option3Btn.setDisable(true);
        q8Option1Btn.setDisable(true);
        q8Option4Btn.setDisable(true);
    }

    public void q8Option3(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q8Option3Btn.setStyle("-fx-background-color: #33BF5E");

        // Disable the other buttons
        q8Option1Btn.setDisable(true);
        q8Option2Btn.setDisable(true);
        q8Option4Btn.setDisable(true);
    }

    public void q8Option4(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q8Option3Btn.setStyle("-fx-background-color: #33BF5E");
        q8Option4Btn.setStyle("-fx-background-color: #FF3333");

        // Disable the other buttons
        q8Option3Btn.setDisable(true);
        q8Option2Btn.setDisable(true);
        q8Option1Btn.setDisable(true);
    }

    public void q9Option1(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q9Option3Btn.setStyle("-fx-background-color: #33BF5E");
        q9Option1Btn.setStyle("-fx-background-color: #FF3333");

        // Disable the other buttons
        q9Option3Btn.setDisable(true);
        q9Option2Btn.setDisable(true);
        q9Option4Btn.setDisable(true);
    }

    public void q9Option2(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q9Option3Btn.setStyle("-fx-background-color: #33BF5E");
        q9Option2Btn.setStyle("-fx-background-color: #FF3333");

        // Disable the other buttons
        q9Option3Btn.setDisable(true);
        q9Option1Btn.setDisable(true);
        q9Option4Btn.setDisable(true);
    }

    public void q9Option3(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q9Option3Btn.setStyle("-fx-background-color: #33BF5E");

        // Disable the other buttons
        q9Option1Btn.setDisable(true);
        q9Option2Btn.setDisable(true);
        q9Option4Btn.setDisable(true);
    }

    public void q9Option4(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q9Option3Btn.setStyle("-fx-background-color: #33BF5E");
        q9Option4Btn.setStyle("-fx-background-color: #FF3333");

        // Disable the other buttons
        q9Option3Btn.setDisable(true);
        q9Option2Btn.setDisable(true);
        q9Option1Btn.setDisable(true);
    }

    public void textNext3(ActionEvent event) throws IOException {
        Game.gameInstance.showAlgebraTestPage4();
    }

}
