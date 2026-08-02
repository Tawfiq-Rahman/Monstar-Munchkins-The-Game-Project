package com.example.return_3.Controllers;

import com.example.return_3.main.Game;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

public class MathController {
    public Button algebraBtn;
    public Button geometryBtn;
    public Button conversionBtn;
    public Button triginometryBtn;
    public Button backButton;
    Game game;

  

    @FXML
    void back(ActionEvent event) throws Exception {
        Game.gameInstance.showSchoolScene();
    }
    
    public void goToAlgebra(ActionEvent event) throws Exception {
        Game.gameInstance.showAlgebraPage1();
    }
    
    public void goToGeometry(ActionEvent event) {
    }
    
    public void goToConversion(ActionEvent event) {
    }
    
    public void goToTrigonometry(ActionEvent event) {
    }
}
