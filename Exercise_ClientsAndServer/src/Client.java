import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
//        try {
//            int port = 99;
//            Socket socket = new Socket("localhost", port);
//            System.out.println("Connection successfully");
//        } catch (Exception e) {
//            System.out.println("Connection unsuccessfully");
//        }

        try {
            int port = 99;
            Socket socket = new Socket("localhost", port);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            Scanner sc = new Scanner(System.in);
            String message;

            while (true) {
                System.out.println("Client: ");
                message = sc.nextLine();
                writer.println(message);
                writer.flush();

                message = reader.readLine();
                System.out.println("Server: " + message);
            }

        } catch (Exception e) {

        }
    }
}