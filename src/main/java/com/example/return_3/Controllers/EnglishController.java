package com.example.return_3.Controllers;

import com.example.return_3.main.Game;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class EnglishController {
    Game game;

    @FXML
    public Button backButton;

    @FXML
    void back(ActionEvent event) throws Exception {

//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/return_3/school.fxml"));
//        Parent root = loader.load();
//        Scene scene= new Scene(root);
//        //scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("school.css")).toExternalForm());
//        Game.primaryStage.setScene(scene);

        Game.gameInstance.showSchoolScene();
    }

}
