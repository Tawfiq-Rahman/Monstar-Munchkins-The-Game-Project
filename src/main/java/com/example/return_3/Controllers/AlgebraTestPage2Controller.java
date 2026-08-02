package com.example.return_3.Controllers;

import com.example.return_3.main.Game;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class AlgebraTestPage2Controller {
    public Button testExitBtn2;
    public Button q4Option1Btn;
    public Button q4Option4Btn;
    public Button q4Option3Btn;
    public Button q4Option2Btn;
    public Button q5Option1Btn;
    public Button q5Option2Btn;
    public Button q5Option3Btn;
    public Button q5Option4Btn;
    public Button q6Option1Btn;
    public Button q6Option2Btn;
    public Button q6Option3Btn;
    public Button q6Option4Btn;
    public Button testNextBtn2;


    public void exitToAlgebraPage7(ActionEvent event) throws IOException {
        Game.gameInstance.showAlgebraPage7();
    }

    public void q4Option1(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q4Option4Btn.setStyle("-fx-background-color: #33BF5E");
        q4Option1Btn.setStyle("-fx-background-color: #FF3333");

        // Disable the other buttons
        q4Option4Btn.setDisable(true);
        q4Option3Btn.setDisable(true);
        q4Option2Btn.setDisable(true);
    }

    public void q4Option4(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q4Option4Btn.setStyle("-fx-background-color: #33BF5E");

        // Disable the other buttons
        q4Option1Btn.setDisable(true);
        q4Option3Btn.setDisable(true);
        q4Option2Btn.setDisable(true);
    }

    public void q4Option3(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q4Option4Btn.setStyle("-fx-background-color: #33BF5E");
        q4Option3Btn.setStyle("-fx-background-color: #FF3333");

        // Disable the other buttons
        q4Option4Btn.setDisable(true);
        q4Option1Btn.setDisable(true);
        q4Option2Btn.setDisable(true);
    }

    public void q4Option2(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q4Option4Btn.setStyle("-fx-background-color: #33BF5E");
        q4Option2Btn.setStyle("-fx-background-color: #FF3333");

        // Disable the other buttons
        q4Option4Btn.setDisable(true);
        q4Option3Btn.setDisable(true);
        q4Option1Btn.setDisable(true);
    }

    public void q5Option1(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q5Option1Btn.setStyle("-fx-background-color: #FF3333");
        q5Option3Btn.setStyle("-fx-background-color: #33BF5E");

        // Disable the other buttons
        q5Option3Btn.setDisable(true);
        q5Option4Btn.setDisable(true);
        q5Option2Btn.setDisable(true);
    }

    public void q5Option2(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q5Option2Btn.setStyle("-fx-background-color: #FF3333");
        q5Option3Btn.setStyle("-fx-background-color: #33BF5E");

        // Disable the other buttons
        q5Option3Btn.setDisable(true);
        q5Option4Btn.setDisable(true);
        q5Option1Btn.setDisable(true);
    }

    public void q5Option3(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q5Option3Btn.setStyle("-fx-background-color: #33BF5E");

        // Disable the other buttons
        q5Option1Btn.setDisable(true);
        q5Option4Btn.setDisable(true);
        q5Option2Btn.setDisable(true);
    }

    public void q5Option4(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q5Option4Btn.setStyle("-fx-background-color: #FF3333");
        q5Option3Btn.setStyle("-fx-background-color: #33BF5E");

        // Disable the other buttons
        q5Option3Btn.setDisable(true);
        q5Option1Btn.setDisable(true);
        q5Option2Btn.setDisable(true);
    }

    public void q6Option1(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q6Option4Btn.setStyle("-fx-background-color: #33BF5E");
        q6Option1Btn.setStyle("-fx-background-color: #FF3333");

        // Disable the other buttons
        q6Option2Btn.setDisable(true);
        q6Option3Btn.setDisable(true);
        q6Option4Btn.setDisable(true);
    }

    public void q6Option2(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q6Option4Btn.setStyle("-fx-background-color: #33BF5E");
        q6Option2Btn.setStyle("-fx-background-color: #FF3333");

        // Disable the other buttons
        q6Option1Btn.setDisable(true);
        q6Option3Btn.setDisable(true);
        q6Option4Btn.setDisable(true);
    }

    public void q6Option3(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q6Option4Btn.setStyle("-fx-background-color: #33BF5E");
        q6Option3Btn.setStyle("-fx-background-color: #FF3333");

        // Disable the other buttons
        q6Option1Btn.setDisable(true);
        q6Option4Btn.setDisable(true);
        q6Option2Btn.setDisable(true);
    }

    public void q6Option4(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q6Option4Btn.setStyle("-fx-background-color: #33BF5E");

        // Disable the other buttons
        q6Option1Btn.setDisable(true);
        q6Option3Btn.setDisable(true);
        q6Option2Btn.setDisable(true);
    }

    public void textNext2(ActionEvent event) throws IOException {
        Game.gameInstance.showAlgebraTestPage3();
    }

}
