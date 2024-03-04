import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            System.out.println("Server is running...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected: " + socket);

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

            // Receive matrix 1 from client
            int[][] matrix1 = receiveMatrix(br);
            // Receive matrix 2 from client
            int[][] matrix2 = receiveMatrix(br);

            // Multiply matrices
            int[][] resultMatrix = multiplyMatrices(matrix1, matrix2);

            // Send result back to client
            sendMatrix(pw, resultMatrix);

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int[][] receiveMatrix(BufferedReader br) throws Exception {
        int rows = Integer.parseInt(br.readLine());
        int cols = Integer.parseInt(br.readLine());

        int[][] matrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            String[] rowValues = br.readLine().split(" ");
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = Integer.parseInt(rowValues[j]);
            }
        }
        return matrix;
    }

    private static void sendMatrix(PrintWriter pw, int[][] matrix) throws Exception {
//        pw.println(matrix.length);
//        pw.println(matrix[0].length);
        for (int[] row : matrix) {
            for (int val : row) {
                pw.print(val + " ");
            }
            pw.println();
        }
    }

    private static int[][] multiplyMatrices(int[][] matrix1, int[][] matrix2) {
        int m = matrix1.length;
        int n = matrix2[0].length;
        int[][] result = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < matrix1[0].length; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }
}
