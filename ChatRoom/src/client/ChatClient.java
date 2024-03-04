package client;

import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    private static final String url = "localhost";
    private static final int PORT = 5000;

    private void executeClient() {
        try {
            Socket socket = new Socket(url, PORT);
            System.out.println("CONNECTED TO SERVER");

            ClientListener clientListener = new ClientListener(socket);
            new Thread(clientListener).start();

            OutputStream outputStream = socket.getOutputStream();
            Scanner sc = new Scanner(System.in);
            while (true) {
                String message = sc.nextLine();
                outputStream.write(message.getBytes());
            }

        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        ChatClient client = new ChatClient();
        client.executeClient();
    }
}
