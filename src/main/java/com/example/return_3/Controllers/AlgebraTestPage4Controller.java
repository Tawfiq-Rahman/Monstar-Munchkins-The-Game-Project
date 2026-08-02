package com.example.return_3.Controllers;

import com.example.return_3.main.Game;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class AlgebraTestPage4Controller {
    public Button testExitBtn4;
    public Button q10Option1Btn;
    public Button q10Option4Btn;
    public Button q10Option3Btn;
    public Button q10Option2Btn;

    public void exitToAlgebraPage7(ActionEvent event) throws IOException {
        Game.gameInstance.showAlgebraPage7();
    }

    public void q10Option1(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q10Option4Btn.setStyle("-fx-background-color: #33BF5E");
        q10Option1Btn.setStyle("-fx-background-color: #FF3333");

        // Disable the other buttons
        q10Option4Btn.setDisable(true);
        q10Option2Btn.setDisable(true);
        q10Option3Btn.setDisable(true);
    }

    public void q10Option4(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q10Option4Btn.setStyle("-fx-background-color: #33BF5E");

        // Disable the other buttons
        q10Option1Btn.setDisable(true);
        q10Option2Btn.setDisable(true);
        q10Option3Btn.setDisable(true);
    }

    public void q10Option3(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q10Option4Btn.setStyle("-fx-background-color: #33BF5E");
        q10Option3Btn.setStyle("-fx-background-color: #FF3333");

        // Disable the other buttons
        q10Option4Btn.setDisable(true);
        q10Option2Btn.setDisable(true);
        q10Option1Btn.setDisable(true);
    }

    public void q10Option2(ActionEvent event) {
        // Change color of q1Option1Btn to green
        q10Option4Btn.setStyle("-fx-background-color: #33BF5E");
        q10Option2Btn.setStyle("-fx-background-color: #FF3333");

        // Disable the other buttons
        q10Option4Btn.setDisable(true);
        q10Option1Btn.setDisable(true);
        q10Option3Btn.setDisable(true);
    }

}
