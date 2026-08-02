package com.example.return_3.globalChat;

import com.example.return_3.main.Game;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientReaderThread implements Runnable {
    private ObjectInputStream ois;
    Game game;

    public ClientReaderThread(Socket socket) throws IOException {
        this.game=Game.gameInstance;
        this.ois = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        try {
            String prevMsg = (String) ois.readObject();
            if (prevMsg != null && !prevMsg.isEmpty()) {
                String[] prevMessages = prevMsg.split("\\$");
                int start = Math.max(0, prevMessages.length - 11);
                for (int i = start; i < prevMessages.length; i++) {
                    game.ui.uiMainGame.appendChat(prevMessages[i]); // Update UI with previous messages
                }
            }
            while (!Thread.currentThread().isInterrupted()) {
                String msg = (String) ois.readObject();
                if (msg != null) {
                    game.ui.uiMainGame.appendChat(msg); // Update UI with new message
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            Thread.currentThread().interrupt();
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
