import java.io.*;
import java.net.*;

public class UDPDNSServer {
    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(9876);
            byte[] receiveData = new byte[1024];
            byte[] sendData;

            System.out.println("DNS Server started. Waiting for client request...");

            // Step 1: Receive domain name from client
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);

            String domain = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Received domain from client: " + domain);

            // Step 2: Resolve domain name to IP
            String ipAddress;
            try {
                InetAddress inetAddress = InetAddress.getByName(domain);
                ipAddress = inetAddress.getHostAddress();
            } catch (UnknownHostException e) {
                ipAddress = "Invalid Domain Name!";
            }

            // Step 3: Send IP address back to client
            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            sendData = ipAddress.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
            serverSocket.send(sendPacket);
            System.out.println("Sent IP address to client: " + ipAddress);

            // Step 4: Close server
            serverSocket.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}

