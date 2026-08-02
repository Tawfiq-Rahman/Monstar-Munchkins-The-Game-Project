package com.example.return_3.Controllers.tictactoe;

import com.example.return_3.gameCenter.ticTacToe.TicTacToe;
import com.example.return_3.main.Game;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class TicTacToeMenuController {
    public Button serverBtn;
    public Button joinBtn;
    public Button back;
    Game game=Game.gameInstance;
    TicTacToe tictactoe= TicTacToe.ticTacInstance;

    public void onServerBtn(ActionEvent actionEvent) throws IOException {
//        Game.gameInstance.ticTacToeGame.showTicTacToeServerPage();
        tictactoe.showServerPage();

    }

    public void onJoinBtn(ActionEvent actionEvent) throws IOException {
//        Game.gameInstance.ticTacToeGame.showTicTacToeRoomPage();
        tictactoe.showClientPage();
    }

    public void onBack(ActionEvent actionEvent) throws Exception {
        game.showGameCenter();

    }
}
