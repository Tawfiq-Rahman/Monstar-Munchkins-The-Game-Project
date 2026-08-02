package com.example.return_3.globalChat;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ClientWriterThread implements Runnable {
    private ObjectOutputStream oos;
    private BlockingQueue<String> messageQueue;

    public ClientWriterThread(Socket socket) throws IOException {
        this.oos = new ObjectOutputStream(socket.getOutputStream());
        this.messageQueue = new LinkedBlockingQueue<>();
    }

    public void sendMessage(String message) {
        try {
            messageQueue.put(message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                String message = messageQueue.take();
                oos.writeObject(message);
                oos.flush();
            }
        } catch (IOException | InterruptedException e) {
            // Exit the loop if interrupted
            Thread.currentThread().interrupt();
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
