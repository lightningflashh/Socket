import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPSever {
    public static void main(String[] args) {
        try {
            int port = 987;
            ServerSocket serverSocket = new ServerSocket(port);
            Socket clientSocket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                String[] numbers = inputLine.split(",");
                int a = Integer.parseInt(numbers[0]);
                int b = Integer.parseInt(numbers[1]);
                int c = Integer.parseInt(numbers[2]);

                String result = solveQuadratic(a, b, c);
                out.println(result);
            }

            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String solveQuadratic(int a, int b, int c) {
        double delta = b * b - 4 * a * c;
        if (delta < 0) {
            return "Phương trình vô nghiệm";
        } else if (delta == 0) {
            double x = (double) -b / (2 * a);
            return "Nghiệm kép: " + x;
        } else {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);
            return "Nghiệm 1: " + x1 + ", Nghiệm 2: " + x2;
        }
    }
}
