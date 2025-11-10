import java.io.*;
import java.net.*;

public class UDPServer {
    public static void main(String[] args) {
        try {
            // Step 1: Create a datagram socket on port 9876
            DatagramSocket serverSocket = new DatagramSocket(9876);
            byte[] receiveData = new byte[1024];
            byte[] sendData;

            System.out.println("UDP Server is running and waiting for client message...");

            while (true) {
                // Step 2: Receive data from client
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received from client: " + clientMessage);

                // Step 3: Echo back the same message
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                sendData = clientMessage.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);

                // Optional: stop the server if "exit" is sent
                if (clientMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Server stopped.");
                    break;
                }
            }

            serverSocket.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
