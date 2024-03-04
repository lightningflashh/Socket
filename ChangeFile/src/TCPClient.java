import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 9876);

            PrintWriter outToServer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Gửi tên file cho server
            outToServer.println("input.txt");
            // Gửi chuỗi cần thay thế cho server
            outToServer.println("s1");
            // Gửi chuỗi thay thế cho server
            outToServer.println("s2");

            // Nhận tên file kết quả từ server
            String resultFileName = inFromServer.readLine();

            System.out.println("File kết quả: " + resultFileName);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
