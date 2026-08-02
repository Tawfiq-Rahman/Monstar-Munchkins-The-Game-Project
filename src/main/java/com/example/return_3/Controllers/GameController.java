package com.example.return_3.Controllers;

import com.example.return_3.gameCenter.puzzGame.PuzzGame;
import com.example.return_3.gameCenter.snakey.Snakey;
import com.example.return_3.gameCenter.spaceInvaders.GameSpaceInvaders;
//import com.example.return_3.gameCenter.ticTacToe.TicTacToe;
import com.example.return_3.gameCenter.ticTacToe.TicTacToe;
import com.example.return_3.main.Game;
import javafx.beans.value.ObservableStringValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.StageStyle;

import java.io.IOException;

public class GameController {

    Game game;
    @FXML
    public Button backButton;
    @FXML
    public Button puzzle;
    @FXML
    public Button spaceInvaders;
    @FXML
    public Button tictactoe;
    public Label coin;
    public Label energy;
    public Button snakeyBtn;


    @FXML
    void back(ActionEvent event) throws Exception {
//        System.out.println("Before entry: "+ Game.gameInstance.player.worldY);
//        //Game.gameInstance.player.worldY+=Game.gameInstance.tileSize*5;
//
//        System.out.println("after entry: "+ Game.gameInstance.player.worldY);
//        Game.gameInstance.keyHandler.setBooleanAll(false);
//        Game.gameInstance.startGame();


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
    }

    @FXML
    void goToPuzzle(ActionEvent event) throws IOException {
        new PuzzGame().puzzGameMenuPage();
    }

    @FXML
    void goToSpaceInvaders(ActionEvent event) {
        System.out.println("ENterign space invaders game");
        Game.gameSpaceInvaders = new GameSpaceInvaders(Game.gameInstance);
        //Game.gameInstance.keyHandler.setBooleanAll(false);
        try {
            Game.gameSpaceInvaders.startGameSpaceInvaders();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @FXML
//    void goToTicTacToe(ActionEvent event) throws IOException {
//        Game.gameInstance.ticTacToeGame= new TicTacToe(Game.gameInstance, Game.gameInstance.gc);
//        Game.gameInstance.ticTacToeGame.showTicTacToeMenuPage();
//    }

    @FXML
    void goToSnakey(ActionEvent event) {
        System.out.println("Entering Snakey game");
        Game.gameSnakey = new Snakey(Game.gameInstance, Game.gameInstance.gc);
        //Game.gameInstance.keyHandler.setBooleanAll(false);
        try {
            Game.gameSnakey.startGameSnakey();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setCoin(int c){
        coin.setText(String.valueOf(c));
    }

    public void goToTicTacToe(ActionEvent actionEvent) throws IOException {
        Game.gameInstance.ticTacToeGame=new TicTacToe();
        TicTacToe.ticTacInstance.showMenuScene();
    }
}
