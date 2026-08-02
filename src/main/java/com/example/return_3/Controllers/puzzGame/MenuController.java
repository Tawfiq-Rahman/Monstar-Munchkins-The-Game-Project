package com.example.return_3.Controllers.puzzGame;


import com.example.return_3.gameCenter.puzzGame.PuzzGame;
import com.example.return_3.main.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuController {


    @FXML
    public Button btn_option;

    @FXML
    public Button btn_exit;

    @FXML
    public Button btn_start;

    @FXML
    public void option(ActionEvent event) {

    }

    @FXML
    public void exit(ActionEvent event) {
        try {
            Game.gameInstance.showGameCenter();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void start(ActionEvent event) {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/return_3/signUp.fxml"));
//        //loader.setController(new MenuController());
//        Parent root = loader.load();
//        Scene scene = new Scene(root);
//        primaryStage.setScene(scene);
       // TestPuzzGame.gameInstance.showGame();
        new PuzzGame().showGame();


    }

}
