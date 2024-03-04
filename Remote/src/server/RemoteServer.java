package server;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class RemoteServer {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected: " + socket.getInetAddress().getHostAddress());

                Thread thread = new Thread(
                        () -> handlerClientRequest(socket)
                );
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void handlerClientRequest(Socket socket) {
       try {
           BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
           PrintWriter writer = new PrintWriter(socket.getOutputStream());
           while (true) {
               String request = reader.readLine();
               if (request.equals("shutdown")) {
                   Runtime.getRuntime().exec("shutdown -s -t 3600");
                   writer.println("Computer is shutting down ...");
                   writer.flush();
               } else if (request.equals("restart")) {
                   Runtime.getRuntime().exec("shutdown -r -t 3600");
                   writer.println("Computer is restarting ...");
                   writer.flush();
               } else if (request.equals("cancel")) {
                   Runtime.getRuntime().exec("shutdown -a");
                   writer.println("Request is canceled");
                   writer.flush();
               } else if (request.equals("screenshot")) {
                   // Capture
                   BufferedImage screenshot = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));

                   ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                   ImageIO.write(screenshot, "png", byteArrayOutputStream);

                   byte[] imageBytes = byteArrayOutputStream.toByteArray();
                   byteArrayOutputStream.close();

                   writer.println(imageBytes.length);
                   writer.flush();
                   socket.getOutputStream().write(imageBytes);
               }
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
    }
}
