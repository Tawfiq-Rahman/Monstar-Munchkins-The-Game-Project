package com.example.return_3.globalChat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    public static ConcurrentHashMap<Socket, ObjectOutputStream> clientOutputStreams = new ConcurrentHashMap<>();
    public static String prevConversation = "";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Server is started on port: " + serverSocket.getLocalPort());

        while (true) {
            Socket socket = serverSocket.accept();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            String clientName = (String) ois.readObject(); // Read the client's name

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            clientOutputStreams.put(socket, oos);
            System.out.println(clientName + " joined");
            oos.writeObject(prevConversation);

            new Thread(new ServerReaderThread(socket, clientName)).start();
        }
    }
}
