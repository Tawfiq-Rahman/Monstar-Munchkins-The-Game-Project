package com.example.return_3.Controllers.tictactoe;

import com.example.return_3.gameCenter.ticTacToe.TicTacToe;
import com.example.return_3.main.Game;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class TicTacToeRoomController {
    public TextField serverIPField;
    public TextField serverPortField;
    public Button joinRoomBtn;
    public Button backBtn;
    TicTacToe ticTacToe = TicTacToe.ticTacInstance;
    public void onBackBtn(ActionEvent actionEvent) throws IOException {
        ticTacToe.showMenuScene();
    }

    public void onJoinRoomBtn(ActionEvent actionEvent) {

        String ip= serverIPField.getText();
        int port = Integer.parseInt(serverPortField.getText());
        //now create logic for start a gme for client view
        ticTacToe.startGame(ticTacToe.clientState,ip,port);
    }
}
