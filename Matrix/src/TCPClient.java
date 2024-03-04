import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 9999);

            Scanner scanner = new Scanner(System.in);
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

            // Input matrix 1
            int[][] matrix1 = inputMatrix(scanner);
            sendMatrix(pw, matrix1);

            // Input matrix 2
            int[][] matrix2 = inputMatrix(scanner);
            sendMatrix(pw, matrix2);

            // Receive result matrix from server
            Scanner serverScanner = new Scanner(socket.getInputStream());
            System.out.println("Result received from server:");
            receiveResult(serverScanner);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void receiveResult(Scanner serverScanner) {
        while (serverScanner.hasNextLine()) {
            System.out.println(serverScanner.nextLine());
        }
    }

    private static int[][] inputMatrix(Scanner scanner) {
        System.out.println("Enter number of rows:");
        int rows = scanner.nextInt();

        System.out.println("Enter number of columns:");
        int cols = scanner.nextInt();

        int[][] matrix = new int[rows][cols];

        System.out.println("Enter elements for the matrix:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        return matrix;
    }

    private static void sendMatrix(PrintWriter pw, int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        pw.println(rows);
        pw.println(cols);

        for (int[] row : matrix) {
            for (int val : row) {
                pw.print(val + " ");
            }
            pw.println();
        }
    }
}
