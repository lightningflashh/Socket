import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) {
        try {
            int port = 987;
            InetAddress ip = InetAddress.getLocalHost();
            Socket user = new Socket(ip.getHostAddress(), port);
            Scanner sc = new Scanner(System.in);
            BufferedReader in = new BufferedReader(new InputStreamReader(user.getInputStream()));
            PrintWriter out = new PrintWriter(user.getOutputStream(),true);
            System.out.println("Nhập các số a, b, c (cách nhau bằng dấu phẩy):");
            String numbers = sc.nextLine();
            out.println(numbers);

            String response = in.readLine();
            System.out.println("Kết quả từ server: " + response);

            user.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
