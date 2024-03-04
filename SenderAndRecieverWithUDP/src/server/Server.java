package server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(9876);

            byte[] receiveData = new byte[1024];
            int count = 0;
            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                String receiveMessage = new String(receivePacket.getData(),0, receivePacket.getLength());

                if (receiveMessage.length() > 0)
                    System.out.println("receiveMessage: " + receiveMessage);

                count++;
                if (count == 1000000000) break;
            }
            socket.close();
        } catch (Exception e) {

        }
    }
}
