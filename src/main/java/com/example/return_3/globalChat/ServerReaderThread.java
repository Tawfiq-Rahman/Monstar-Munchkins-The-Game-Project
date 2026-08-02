package com.example.return_3.globalChat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerReaderThread implements Runnable {
    private Socket socket;
    private String name;
    private ObjectInputStream ois;

    public ServerReaderThread(Socket socket, String name) throws IOException {
        this.socket = socket;
        this.name = name;
        this.ois = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        try {
            while (true) {
                String msg = (String) ois.readObject();
                if (msg == null) break;

                String msgToSend = name + "  " + msg;
                System.out.println(msgToSend);
                Server.prevConversation += "$" + msgToSend;

                for (ObjectOutputStream oos : Server.clientOutputStreams.values()) {
                    oos.writeObject(msgToSend);
                    oos.flush();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(name + " disconnected!");
            Server.clientOutputStreams.remove(socket);
            try {
                ois.close();
                socket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}