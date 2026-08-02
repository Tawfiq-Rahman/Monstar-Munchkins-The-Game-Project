package com.example.return_3.Controllers.tictactoe;

import com.example.return_3.gameCenter.ticTacToe.TicTacToe;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class TicTacToeGameOverController {
    public Button playAgainBtn;
    public Button backBtn;
    TicTacToe ticTacToe = TicTacToe.ticTacInstance;
    public void onPlayAgain(ActionEvent actionEvent) {
        clearBoard();
        ticTacToe.startGame(3,"",0);
    }

    public void onBack(ActionEvent actionEvent) throws IOException {
        // Close connections
        if (ticTacToe.isServer) {
            // If the instance is a server, close the server socket
            try {
                ticTacToe.serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // If the instance is a client, close the socket and streams
            try {
                ticTacToe.socket.close();
                ticTacToe.oos.close();
                ticTacToe.ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ticTacToe.showMenuScene();
    }




    private void clearBoard() {
        // Clear the game board data
        for (int i = 0; i < ticTacToe.spaces.length; i++) {
            ticTacToe.spaces[i] = null;
        }



            // Reset game state variables
        ticTacToe.yourTurn = false;
        ticTacToe.circle = true;
        ticTacToe.accepted = true;
        ticTacToe.unableToCommunicateWithOpponent = false;
        ticTacToe.won = false;
        ticTacToe.enemyWon = false;
        ticTacToe.tie = false;
        if (ticTacToe.isServer){
            ticTacToe.yourTurn = true;
            ticTacToe.circle = false;
        }
    }
}
