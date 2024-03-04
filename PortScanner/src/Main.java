import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        //checkPort("localhost");
        checkPort("online.hcmute.edu.vn");
    }

    public static void checkPort(String urlString) {
        int start = 1;
        int end = 65535; // Maximum port number

        System.out.println("Scanning ports on host: " + urlString);

        for (int i = start; i <= end; i++) {
            try {
                Socket socket = new Socket(urlString, i);
                System.out.println("Port " + i + " is open");
                socket.close();
            } catch (Exception e) {
                // Port is closed or unreachable, continue silently
            }
        }
        System.out.println("Port scanning complete!");
    }
}
