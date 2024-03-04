import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    public static void main(String[] args) {
        try {
            int port = 99;
            ServerSocket serverSocket = new ServerSocket(port);

            // Accept a connection from clients
            // Socket clientSocket = serverSocket.accept();

            // Accept more than a connection from clients
            while (true) {
                Socket clientSocket = serverSocket.accept();
                MyProcess mp = new MyProcess(clientSocket);
                mp.start();
            }

            // Share information
            //Thread.sleep(5000);

            // Interrupt connection
            // clientSocket.close();
            //serverSocket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}