import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ServiceClient {
    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;

    public ServiceClient(Socket socket) {
        try {
            this.socket = socket;
            this.printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendRequest(String request) {
        printWriter.println(request);
        printWriter.flush();
    }

    public String receiveResponse() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void close() {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1235);
            ServiceClient client = new ServiceClient(socket);
            Scanner sc = new Scanner(System.in);

            System.out.println("Choose a request:");
            System.out.println("1. Convert to UPPERCASE");
            System.out.println("2. Convert to lowercase");
            System.out.println("3. Delete substring");
            System.out.println("4. Insert substring");
            int choice = Integer.parseInt(sc.nextLine());
            String request = "";

            switch (choice) {
                case 1:
                    System.out.println("Enter input string: ");
                    request = "UPPER " + sc.nextLine();
                    break;
                case 2:
                    System.out.println("Enter input string: ");
                    request = "LOWER " + sc.nextLine();
                    break;
                case 3:
                    System.out.println("Enter input string: ");
                    String inputString = sc.nextLine();
                    System.out.println("Enter start index: ");
                    int startIndex = Integer.parseInt(sc.nextLine());
                    System.out.println("Enter length: ");
                    int length = Integer.parseInt(sc.nextLine());
                    request = "DELETE " + inputString + " " + startIndex + " " + length;
                    break;
                case 4:
                    System.out.println("Enter input string: ");
                    String inputString2 = sc.nextLine();
                    System.out.println("Enter insert index: ");
                    int insertIndex = Integer.parseInt(sc.nextLine());
                    System.out.println("Enter substring to insert: ");
                    String substring = sc.nextLine();
                    request = "INSERT " + inputString2 + " " + insertIndex + " " + substring;
                    break;
                default:
                    System.out.println("Invalid choice!");
                    client.close();
                    return;
            }

            client.sendRequest(request);

            // Nhận và in phản hồi từ server
            String response = client.receiveResponse();
            System.out.println("Server response: " + response);

            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
