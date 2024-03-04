import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.printWriter = new PrintWriter(socket.getOutputStream());
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String request;
        try {
            while ((request = bufferedReader.readLine()) != null) {
                String response = handleRequest(request);
                if (response != null) {
                    printWriter.println(response);
                    printWriter.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String handleRequest(String request) {
        String[] parts = request.split(" ");
        String command = parts[0];
        System.out.println(command);

        if (parts.length < 2) {
            return "Invalid request format";
        }

        String inputString = parts[1];
        String result = "";

        switch (command) {
            case "UPPER" -> result = inputString.toUpperCase();
            case "LOWER" -> result = inputString.toLowerCase();
            case "DELETE" -> {
                if (parts.length < 4) {
                    return "Invalid request format";
                }
                int startIndex = Integer.parseInt(parts[2]);
                int length = Integer.parseInt(parts[3]);
                result = deleteSubstring(inputString, startIndex, length);
            }
            case "INSERT" -> {
                if (parts.length < 4) {
                    return "Invalid request format";
                }
                int insertIndex = Integer.parseInt(parts[2]);
                String substring = parts[3];
                result = insertSubstring(inputString, insertIndex, substring);
            }
            default -> result = "Invalid command";
        }
        return result;
    }

    private String deleteSubstring(String inputString, int startIndex, int length) {
        if (startIndex < 0 || startIndex >= inputString.length() || length < 0 || startIndex + length > inputString.length()) {
            return "Invalid index or length";
        }
        StringBuilder sb = new StringBuilder(inputString);
        sb.delete(startIndex, startIndex + length);
        return sb.toString();
    }

    private String insertSubstring(String inputString, int insertIndex, String substring) {
        if (insertIndex < 0 || insertIndex > inputString.length()) {
            return "Invalid index";
        }
        StringBuilder sb = new StringBuilder(inputString);
        sb.insert(insertIndex, substring);
        return sb.toString();
    }
}
