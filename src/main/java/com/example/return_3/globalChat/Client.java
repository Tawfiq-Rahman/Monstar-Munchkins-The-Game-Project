package com.example.return_3.globalChat;

import com.example.return_3.main.Game;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    static String name;
    public static ClientWriterThread clientWriterThread;
    public static Thread threadClientWriter;
    public static Thread threadClientReader;

    public static void startClient() throws IOException {
        name= Game.gameInstance.user.getUsername();
        Socket socket = new Socket("localhost", 8080);
        System.out.println("Connected");

        // Send the client's name to the server
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(name);
        oos.flush();

        clientWriterThread = new ClientWriterThread(socket);
        threadClientWriter = new Thread(clientWriterThread);
        threadClientWriter.start();

        threadClientReader = new Thread(new ClientReaderThread(socket));
        threadClientReader.start();
    }

    public static void stopClient() {
        if (threadClientWriter != null) {
            threadClientWriter.interrupt();
        }
        if (threadClientReader != null) {
            threadClientReader.interrupt();
        }
    }
}
