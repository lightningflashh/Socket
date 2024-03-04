package server;

import client.ClientHandler;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
    private static final int PORT = 5000;
    private List<ClientHandler> clientHandlers = new ArrayList<>();

    public void executeServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started. Listening on port: " + PORT);

            // clients connect to server
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress());

                ClientHandler clientHandler = new ClientHandler(clientSocket, System.currentTimeMillis() + "", this);
                clientHandlers.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void broadcastMessage(String id, String message) {
        for (ClientHandler client : clientHandlers) {
            if (!client.getId().equals(id))
                client.sendMessage(id + ": " + message);
        }
    }

    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
        chatServer.executeServer();
    }
}
