import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    private static FileReader fileReader;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9876);
            Socket socket = serverSocket.accept();


            Thread thread = new Thread(
                    () -> handlerClientRequest(socket)
            );
            thread.start();

        } catch (Exception e) {

        }
    }

    private static void handlerClientRequest(Socket socket) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            String fileName = in.readLine();
            String s1 = in.readLine(); // string needs replacing
            String s2 = in.readLine(); // string replace

            openFile(fileName);

            //replace(fileName, s1, s2);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean openFile(String filename) {
        try {
            fileReader = new FileReader("D:\\Socket\\ChangeFile\\" + filename);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static String nextLine() {
        return null;
    }
    public static boolean closeFile() {
        try {
            fileReader.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public int replace(String s, String s1, String s2) {
        try {
            String line;
            StringBuilder newContent = new StringBuilder();

            while ((line = fileReader.readLine()) != null) {
                newContent.append(line.replaceAll(s1, s2)).append(System.lineSeparator());
            }

            // Ghi lại nội dung đã thay thế vào file
            FileWriter writer = new FileWriter("temp.txt");
            writer.write(newContent.toString());
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return replaceCount;
    }
}
