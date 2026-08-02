package com.example.return_3.Controllers.tictactoe;

import com.example.return_3.gameCenter.ticTacToe.TicTacToe;
import com.example.return_3.main.Game;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class TicTacToeServerController {
    public TextField portNumberField;
    public Button startGameBtn;
    public Button backBtn;
    TicTacToe ticTacToe = TicTacToe.ticTacInstance;
    public void onStartGameBtn(ActionEvent actionEvent) {

        String portText = portNumberField.getText();


        // Check if the portText is a valid integer
        try {
            int serverPort = Integer.parseInt(portText);

            // Check if the port number is within the valid range
            if (serverPort >= 0 && serverPort <= 65535) {
                // Port number is valid, you can proceed with server start logic here

//                Server server= new Server();
//                server.start(serverPort);
                ticTacToe.startGame(ticTacToe.serverState,"",serverPort); //no need for ipaddress here;

            } else {
                // Port number is out of range, display an error message
                showAlert("Invalid Port Number", "Port number should be between 0 and 65535.");
            }
        } catch (NumberFormatException e) {
            // Input is not a valid integer, display an error message
            showAlert("Invalid Input", "Please enter a valid integer for the port number.");
        }

    }

    public void onBackBtn(ActionEvent actionEvent) throws IOException {
       ticTacToe.showMenuScene();
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
