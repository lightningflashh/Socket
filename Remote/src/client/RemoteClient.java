package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class RemoteClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 9999);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            Scanner sc = new Scanner(System.in);
            boolean exit = false;
            while (!exit) {
                System.out.println("\nMENU: ");
                System.out.println("1. Shutdown");
                System.out.println("2. Restart");
                System.out.println("3. Cancel shutdown or restart");
                System.out.println("4. Screenshot");
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1 -> {
                        writer.println("shutdown");
                        writer.flush();
                        System.out.println(reader.readLine());
                    }
                    case 2 -> {
                        writer.println("restart");
                        writer.flush();
                        writer.println(reader.readLine());
                    }
                    case 3 -> {
                        writer.println("cancel");
                        writer.flush();
                        writer.println(reader.readLine());
                    } case 4 -> {
                        writer.println("screenshot");
                        writer.flush();
                        int imageSize = Integer.parseInt(reader.readLine());
                        byte[] imageBytes = new byte[imageSize];
                        int byteRead = socket.getInputStream().read(imageBytes);
                        if (byteRead > 0) {
                            System.out.println("Create name: ");
                            String fileName = sc.nextLine();
                            Path imagePath = Paths.get(fileName + ".png");
                            Files.write(imagePath, imageBytes);
                            System.out.println("Done!");
                        }
                    }
                    default -> throw new AssertionError();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
