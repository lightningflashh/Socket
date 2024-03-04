package client;

import server.ChatServer;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket mySocket;
    private String id;
    ChatServer chatServer;
    private InputStream inputStream;
    private OutputStream outputStream;

    public ClientHandler(Socket mySocket, String id, ChatServer chatServer) {
        this.mySocket = mySocket;
        this.id = id;
        this.chatServer = chatServer;
        try {
            this.inputStream = mySocket.getInputStream();
            this.outputStream = mySocket.getOutputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        try {
            outputStream.write(message.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }


    @Override
    public void run() {
        try {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                String message = new String(buffer, 0, bytesRead);
                chatServer.broadcastMessage(this.id, message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
